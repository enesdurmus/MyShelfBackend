package com.enesd.myshelfbackend.repository.elasticsearch.impl;

import co.elastic.clients.elasticsearch._types.query_dsl.FuzzyQuery;
import com.enesd.myshelfbackend.model.documents.MediaContentDocument;
import com.enesd.myshelfbackend.repository.elasticsearch.custom.IMediaContentDocumentRepositoryCustom;
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
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class MediaContentDocumentRepositoryImpl implements IMediaContentDocumentRepositoryCustom {

    private static final Logger logger = LoggerFactory.getLogger(MediaContentDocumentRepositoryImpl.class);
    private final ElasticsearchOperations elasticsearchOperations;

    @Override
    public List<MediaContentDocument> findWithSearchTerm(String searchTerm) {
        IndexCoordinates index = IndexCoordinates.of("media_contents");
        final Query searchQuery = NativeQuery.builder()
                .withQuery(query -> query.fuzzy(new FuzzyQuery.Builder()
                        .field("title")
                        .value(searchTerm)
                        .fuzziness("AUTO")
                        .prefixLength(0)
                        .build()))
                .build();

        SearchHits<MediaContentDocument> searchHits = elasticsearchOperations.search(searchQuery, MediaContentDocument.class, index);
        return searchHits.getSearchHits().stream().map(SearchHit::getContent).collect(Collectors.toList());
    }
}