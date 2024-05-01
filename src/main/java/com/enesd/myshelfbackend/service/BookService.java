package com.enesd.myshelfbackend.service;

import com.enesd.myshelfbackend.model.entities.BookEntity;
import com.enesd.myshelfbackend.repository.BookEntityRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    private final BookEntityRepository bookRepository;

    public List<BookEntity> searchRelatedBooks(String searchText) {
        return bookRepository.findByTitleContaining(searchText);
    }

    public BookEntity findBookById(int bookId) {
        return bookRepository.findById(bookId).orElseThrow(EntityNotFoundException::new);
    }
}
