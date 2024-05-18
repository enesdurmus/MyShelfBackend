package com.enesd.myshelfbackend.repository.elasticsearch.custom;

import com.enesd.myshelfbackend.model.documents.MediaContentDocument;

import java.util.List;

public interface IMediaContentDocumentRepositoryCustom {
    List<Integer> findAllIds();

    List<MediaContentDocument> findWithSearchTerm(String searchTerm);
}
