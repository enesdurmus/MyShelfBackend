package com.enesd.myshelfbackend.jobs;

import com.enesd.myshelfbackend.model.abstracts.Book;
import com.enesd.myshelfbackend.model.documents.BookDocument;
import com.enesd.myshelfbackend.model.entities.BookEntity;
import com.enesd.myshelfbackend.model.entities.SyncStatus;
import com.enesd.myshelfbackend.repository.elasticsearch.BookDocumentRepository;
import com.enesd.myshelfbackend.repository.jpa.BookEntityRepository;
import com.enesd.myshelfbackend.repository.jpa.SyncStatusRepository;
import com.enesd.myshelfbackend.utils.CustomModelMapper;
import lombok.AllArgsConstructor;
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
public class ElasticBookSync {
    private final Logger logger = LoggerFactory.getLogger(ElasticBookSync.class);
    private final BookEntityRepository bookEntityRepository;
    private final BookDocumentRepository bookDocumentRepository;
    private final SyncStatusRepository syncStatusRepository;
    private final CustomModelMapper modelMapper;

    @Transactional
    @Scheduled(fixedRateString = "60000")
    public void syncBooks() {
        try {
            SyncStatus syncStatus = syncStatusRepository.findByEntityName(Book.class.getName())
                    .orElse(null);

            Instant lastSyncTime;
            Instant now = Instant.now();
            boolean isInitialSync = (syncStatus == null);
            boolean isSynchronizing = true;
            int pageNumber = 0;
            lastSyncTime = isInitialSync ? Instant.ofEpochSecond(0) : syncStatus.getLastSyncTime();

            do {
                Pageable pageable = PageRequest.of(pageNumber, 100, Sort.by("updatedAt"));
                List<BookEntity> bookEntities = bookEntityRepository.findAllByUpdatedAtIsGreaterThanEqual(lastSyncTime, pageable);
                if (!bookEntities.isEmpty()) {
                    List<BookDocument> bookDocuments = bookEntities
                            .stream()
                            .map(book -> modelMapper.map(book, BookDocument.class)).toList();

                    bookDocumentRepository.saveAll(bookDocuments);
                    pageNumber++;
                    logger.info("Page is not empty continue to sync operation! Current page:{}", pageNumber);
                } else {
                    isSynchronizing = false;
                    logger.info("Page is empty finish sync operation! Current page:{}", pageNumber);
                }
            } while (isSynchronizing);

            if (isInitialSync) {
                syncStatus = new SyncStatus();
                syncStatus.setEntityName(Book.class.getName());
            }

            syncStatus.setLastSyncTime(now);
            syncStatusRepository.save(syncStatus);

            logger.info("Sync for {} completed successfully at {}", Book.class.getName(), now);
        } catch (Exception e) {
            logger.error("Error during sync for entity: " + Book.class.getName(), e);
        }
    }
}
