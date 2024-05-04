package com.enesd.myshelfbackend.jobs;

import com.enesd.myshelfbackend.model.documents.BookDocument;
import com.enesd.myshelfbackend.repository.elasticsearch.BookDocumentRepository;
import com.enesd.myshelfbackend.repository.jpa.BookEntityRepository;
import lombok.AllArgsConstructor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.SearchTemplateQuery;
import org.springframework.data.elasticsearch.core.query.SearchTemplateQueryBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ElasticBookSynchronizer {

    private static final Logger logger = LoggerFactory.getLogger(ElasticBookSynchronizer.class);
    private final BookDocumentRepository bookDocumentRepository;
    private final BookEntityRepository bookEntityRepository;

    @Scheduled(cron = "0 * * * * *")
    @SchedulerLock(name = "ElasticBookSynchronizer.synchronizeBooks", lockAtLeastFor = "PT15S", lockAtMostFor = "PT30S")
    public void synchronizeBooks() {
        bookDocumentRepository.findAllIds();
    }
}
