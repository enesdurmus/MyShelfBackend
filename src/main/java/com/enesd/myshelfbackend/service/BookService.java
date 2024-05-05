package com.enesd.myshelfbackend.service;

import com.enesd.myshelfbackend.model.base.Book;
import com.enesd.myshelfbackend.model.documents.BookDocument;
import com.enesd.myshelfbackend.model.entities.BookEntity;
import com.enesd.myshelfbackend.repository.elasticsearch.BookDocumentRepository;
import com.enesd.myshelfbackend.repository.jpa.BookEntityRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    private final BookEntityRepository bookEntityRepository;
    private final BookDocumentRepository bookDocumentRepository;

    public List<BookDocument> searchRelatedBooks(String searchTerm) {
        return bookDocumentRepository.findWithSearchTerm(searchTerm);
    }

    public BookEntity findBookById(int bookId) {
        return bookEntityRepository.findById(bookId).orElseThrow(EntityNotFoundException::new);
    }
}
