package com.enesd.myshelfbackend.repository.elasticsearch.custom;


import com.enesd.myshelfbackend.model.documents.BookDocument;

import java.util.List;

public interface IBookDocumentRepositoryCustom {

    List<Integer> findAllIds();

    List<BookDocument> findWithSearchTerm(String searchTerm);
}
