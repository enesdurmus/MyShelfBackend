package com.enesd.myshelfbackend.repository;

import com.enesd.myshelfbackend.model.documents.BookDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BookDocumentRepository extends ElasticsearchRepository<BookDocument, Integer> {
}
