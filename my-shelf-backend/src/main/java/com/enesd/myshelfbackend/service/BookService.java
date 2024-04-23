package com.enesd.myshelfbackend.service;

import com.enesd.myshelfbackend.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

}
