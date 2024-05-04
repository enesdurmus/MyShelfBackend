package com.enesd.myshelfbackend.repository.elasticsearch.impl;

import com.enesd.myshelfbackend.jobs.ElasticBookSynchronizer;
import com.enesd.myshelfbackend.model.documents.BookDocument;
import com.enesd.myshelfbackend.repository.elasticsearch.custom.BookDocumentCustomRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.SearchTemplateQuery;
import org.springframework.data.elasticsearch.core.query.SearchTemplateQueryBuilder;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
class BookDocumentCustomRepositoryImpl implements BookDocumentCustomRepository {
    private static final Logger logger = LoggerFactory.getLogger(BookDocumentCustomRepositoryImpl.class);
    private final ElasticsearchTemplate elasticsearchTemplate;

    public List<Integer> findAllIds() {
        SearchTemplateQuery searchQuery = new SearchTemplateQueryBuilder()
                .withSource("books")
                .withFields("id")
                .build();

        SearchHits<BookDocument> searchHits = elasticsearchTemplate.search(searchQuery, BookDocument.class);
        List<Integer> elasticsearchIds = searchHits.get().map(hit -> hit.getContent().getId()).collect(Collectors.toList());
        logger.info(String.valueOf(elasticsearchIds.size()));
        return null;
    }
}
