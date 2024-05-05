package com.enesd.myshelfbackend.repository.elasticsearch.impl;

import co.elastic.clients.elasticsearch._types.query_dsl.FuzzyQuery;
import com.enesd.myshelfbackend.model.documents.BookDocument;
import com.enesd.myshelfbackend.repository.elasticsearch.custom.IBookDocumentRepositoryCustom;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.SearchHitsIterator;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
class BookDocumentRepositoryImpl implements IBookDocumentRepositoryCustom {
    private static final Logger logger = LoggerFactory.getLogger(BookDocumentRepositoryImpl.class);
    private final ElasticsearchOperations elasticsearchOperations;

    @Override
    public List<Integer> findAllIds() {
        IndexCoordinates index = IndexCoordinates.of("books");
        String[] includeFields = new String[]{"id"};

        Query searchQuery = NativeQuery.builder()
                .withQuery(q -> q
                        .matchAll(ma -> ma))
                .withPageable(PageRequest.of(0, 10000))
                .withSourceFilter(new FetchSourceFilter(includeFields, null))
                .build();

        SearchHitsIterator<BookDocument> stream = elasticsearchOperations.searchForStream(searchQuery, BookDocument.class,
                index);

        List<Integer> bookIds = new ArrayList<>();
        while (stream.hasNext()) {
            bookIds.add(stream.next().getContent().getId());
        }

        stream.close();
        logger.info(String.valueOf(bookIds.size()));
        return bookIds;
    }

    @Override
    public List<BookDocument> findWithSearchTerm(String searchTerm) {
        final Query searchQuery = NativeQuery.builder()
                .withQuery(query -> query.fuzzy(new FuzzyQuery.Builder()
                        .field("title")
                        .value(searchTerm)
                        .fuzziness("AUTO")
                        .prefixLength(0)
                        .build()))
                .build();

        SearchHits<BookDocument> searchHits = elasticsearchOperations.search(searchQuery, BookDocument.class);
        return searchHits.getSearchHits().stream().map(SearchHit::getContent).collect(Collectors.toList());
    }
}

