package com.enesd.myshelfbackend.service;

import com.enesd.myshelfbackend.model.entities.Book;
import com.enesd.myshelfbackend.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> searchRelatedBooks(String searchText) {
        return bookRepository.findByTitleContaining(searchText);
    }
}
