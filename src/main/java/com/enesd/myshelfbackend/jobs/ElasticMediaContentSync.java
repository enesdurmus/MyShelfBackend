package com.enesd.myshelfbackend.jobs;

import com.enesd.myshelfbackend.model.abstracts.Book;
import com.enesd.myshelfbackend.model.abstracts.MediaContent;
import com.enesd.myshelfbackend.model.documents.BookDocument;
import com.enesd.myshelfbackend.model.documents.MediaContentDocument;
import com.enesd.myshelfbackend.model.entities.BookEntity;
import com.enesd.myshelfbackend.model.entities.MediaContentEntity;
import com.enesd.myshelfbackend.model.entities.SyncStatus;
import com.enesd.myshelfbackend.repository.elasticsearch.MediaContentDocumentRepository;
import com.enesd.myshelfbackend.repository.jpa.MediaContentEntityRepository;
import com.enesd.myshelfbackend.repository.jpa.SyncStatusRepository;
import com.enesd.myshelfbackend.utils.CustomModelMapper;
import lombok.AllArgsConstructor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Component
@AllArgsConstructor
public class ElasticMediaContentSync {
    private final Logger logger = LoggerFactory.getLogger(ElasticMediaContentSync.class);
    private final MediaContentEntityRepository mediaContentEntityRepository;
    private final MediaContentDocumentRepository mediaContentDocumentRepository;
    private final SyncStatusRepository syncStatusRepository;
    private final CustomModelMapper modelMapper;

    @Transactional
    @Scheduled(fixedRateString = "60000")
    @SchedulerLock(name = "ElasticMediaContentSync.syncMediaContents", lockAtLeastFor = "PT15S", lockAtMostFor = "PT30S")
    public void syncMediaContents() {
        try {
            SyncStatus syncStatus = syncStatusRepository.findByEntityName(MediaContent.class.getName())
                    .orElse(null);

            Instant lastSyncTime;
            Instant now = Instant.now();
            boolean isInitialSync = (syncStatus == null);
            boolean isSynchronizing = true;
            int pageNumber = 0;
            lastSyncTime = isInitialSync ? Instant.ofEpochSecond(0) : syncStatus.getLastSyncTime();

            do {
                Pageable pageable = PageRequest.of(pageNumber, 100, Sort.by("updatedAt"));
                List<MediaContentEntity> mediaContentEntities = mediaContentEntityRepository.findAllByUpdatedAtIsGreaterThanEqual(lastSyncTime, pageable);
                if (!mediaContentEntities.isEmpty()) {
                    List<MediaContentDocument> mediaContentDocuments = mediaContentEntities
                            .stream()
                            .map(book -> modelMapper.map(book, MediaContentDocument.class)).toList();

                    mediaContentDocumentRepository.saveAll(mediaContentDocuments);
                    pageNumber++;
                    logger.info("Page is not empty continue to sync operation! Current page:{}", pageNumber);
                } else {
                    isSynchronizing = false;
                    logger.info("Page is empty finish sync operation! Current page:{}", pageNumber);
                }
            } while (isSynchronizing);

            if (isInitialSync) {
                syncStatus = new SyncStatus();
                syncStatus.setEntityName(MediaContent.class.getName());
            }

            syncStatus.setLastSyncTime(now);
            syncStatusRepository.save(syncStatus);

            logger.info("Sync for {} completed successfully at {}", MediaContent.class.getName(), now);
        } catch (Exception e) {
            logger.error("Error during sync for entity: " + MediaContent.class.getName(), e);
        }
    }
}
