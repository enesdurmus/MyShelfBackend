package com.enesd.myshelfbackend.repository.elasticsearch.impl;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import com.enesd.myshelfbackend.model.documents.BookDocument;
import com.enesd.myshelfbackend.repository.elasticsearch.custom.IBookDocumentRepositoryCustom;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.*;

@AllArgsConstructor
class BookDocumentRepositoryImpl implements IBookDocumentRepositoryCustom {
    private static final Logger logger = LoggerFactory.getLogger(BookDocumentRepositoryImpl.class);
    private final ElasticsearchOperations elasticsearchOperations;

    @Override
    public Integer[] findAllIds() {
        String[] includeFields = new String[]{"id"};

        Query query = NativeQuery.builder()
                .withQuery(q -> q.matchAll(QueryBuilders.matchAll().build()))
                .withSourceFilter(new FetchSourceFilter(includeFields, null)).build();

        SearchHits<BookDocument> searchHits = elasticsearchOperations.search(query, BookDocument.class);
        Integer[] ids = searchHits.getSearchHits()
                .stream().map(sh -> sh.getContent().getId())
                .toArray(Integer[]::new);

        return ids;
    }
}

