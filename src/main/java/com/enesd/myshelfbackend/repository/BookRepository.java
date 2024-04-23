package com.enesd.myshelfbackend.repository;

import com.enesd.myshelfbackend.model.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByTitleContaining(String searchText);
}
