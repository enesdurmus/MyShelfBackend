package com.enesd.myshelfbackend.repository.elasticsearch;

import com.enesd.myshelfbackend.model.documents.MediaContentDocument;
import com.enesd.myshelfbackend.repository.elasticsearch.custom.IMediaContentDocumentRepositoryCustom;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface MediaContentDocumentRepository extends ElasticsearchRepository<MediaContentDocument, Integer>, IMediaContentDocumentRepositoryCustom {
}
