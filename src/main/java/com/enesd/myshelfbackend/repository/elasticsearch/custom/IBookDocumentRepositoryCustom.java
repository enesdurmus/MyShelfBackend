package com.enesd.myshelfbackend.repository.elasticsearch.custom;


import com.enesd.myshelfbackend.model.documents.BookDocument;

import java.util.List;

public interface IBookDocumentRepositoryCustom {
    List<BookDocument> findWithSearchTerm(String searchTerm);
}
