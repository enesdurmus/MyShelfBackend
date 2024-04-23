package com.enesd.myshelfbackend.repository;

import com.enesd.myshelfbackend.model.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
