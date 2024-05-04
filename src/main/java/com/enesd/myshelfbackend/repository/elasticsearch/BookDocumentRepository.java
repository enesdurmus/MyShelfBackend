package com.enesd.myshelfbackend.repository.elasticsearch;

import com.enesd.myshelfbackend.model.documents.BookDocument;
import com.enesd.myshelfbackend.repository.elasticsearch.custom.BookDocumentCustomRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BookDocumentRepository extends ElasticsearchRepository<BookDocument, Integer>, BookDocumentCustomRepository {
}
