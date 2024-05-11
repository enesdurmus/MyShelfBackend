package com.enesd.myshelfbackend.jobs;

import com.enesd.myshelfbackend.model.documents.BookDocument;
import com.enesd.myshelfbackend.model.entities.BookEntity;
import com.enesd.myshelfbackend.repository.elasticsearch.BookDocumentRepository;
import com.enesd.myshelfbackend.repository.jpa.BookEntityRepository;
import lombok.AllArgsConstructor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ElasticBookSynchronizer {

    private static final Logger logger = LoggerFactory.getLogger(ElasticBookSynchronizer.class);
    private final BookDocumentRepository bookDocumentRepository;
    private final BookEntityRepository bookEntityRepository;
    private final ModelMapper modelMapper;

    @Scheduled(cron = "0/10 * * ? * *")
    @SchedulerLock(name = "ElasticBookSynchronizer.synchronizeBooksWithRDBM", lockAtLeastFor = "PT15S", lockAtMostFor = "PT30S")
    public void synchronizeBooksWithRDBM() {
        List<Integer> elasticBookIds = bookDocumentRepository.findAllIds();

        if (elasticBookIds.isEmpty()) {
            logger.info("There is no entry in ES add dummy value");
            elasticBookIds.add(0);
        }

        boolean isSynchronizing = true;
        int pageNumber = 0;

        do {
            Pageable pageable = PageRequest.of(pageNumber, 1000, Sort.by("id"));
            List<BookEntity> bookEntities = bookEntityRepository.findByIdNotIn(elasticBookIds, pageable);

            if (!bookEntities.isEmpty()) {
                List<BookDocument> bookDocuments = bookEntities
                        .stream()
                        .map(user -> modelMapper.map(user, BookDocument.class)).toList();

                bookDocumentRepository.saveAll(bookDocuments);
                pageNumber++;
                logger.info("Page is not empty continue to sync operation! Page number:{}", pageNumber);
            } else {
                isSynchronizing = false;
                logger.info("Page is empty finish sync operation! Page number:{}", pageNumber);
            }
        } while (isSynchronizing);
    }
}
