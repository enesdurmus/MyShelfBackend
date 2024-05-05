package com.enesd.myshelfbackend.jobs;

import com.enesd.myshelfbackend.repository.elasticsearch.BookDocumentRepository;
import com.enesd.myshelfbackend.repository.jpa.BookEntityRepository;
import lombok.AllArgsConstructor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;

@Component
@AllArgsConstructor
public class ElasticBookSynchronizer {

    private static final Logger logger = LoggerFactory.getLogger(ElasticBookSynchronizer.class);
    private final BookDocumentRepository bookDocumentRepository;
    private final BookEntityRepository bookEntityRepository;

    @Scheduled(cron = "0 * * * * *")
    @SchedulerLock(name = "ElasticBookSynchronizer.synchronizeBooks", lockAtLeastFor = "PT15S", lockAtMostFor = "PT30S")
    public void synchronizeBooks() {
        Integer[] elasticBookIds = bookDocumentRepository.findAllIds();
        Arrays.stream(elasticBookIds).forEach(s -> logger.info(s.toString()));
    }
}
