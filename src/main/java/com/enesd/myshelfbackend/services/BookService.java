package com.enesd.myshelfbackend.services;

import com.enesd.myshelfbackend.model.documents.BookDocument;
import com.enesd.myshelfbackend.model.entities.BookEntity;
import com.enesd.myshelfbackend.model.interfaces.ISearchable;
import com.enesd.myshelfbackend.repository.elasticsearch.BookDocumentRepository;
import com.enesd.myshelfbackend.repository.jpa.BookEntityRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class BookService implements ISearchable {
    private final BookEntityRepository bookEntityRepository;
    private final BookDocumentRepository bookDocumentRepository;

    public List<BookDocument> searchRelatedBooks(String searchTerm) {
        return bookDocumentRepository.findWithSearchTerm(searchTerm);
    }

    public BookEntity findBookById(Long bookId) {
        return bookEntityRepository.findById(bookId).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Object> searchAllRelevant(String searchTerm) {
        return Collections.singletonList(searchRelatedBooks(searchTerm));
    }
}
