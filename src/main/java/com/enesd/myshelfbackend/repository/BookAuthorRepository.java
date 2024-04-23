package com.enesd.myshelfbackend.repository;

import com.enesd.myshelfbackend.model.entities.BookAuthor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookAuthorRepository extends JpaRepository<BookAuthor, Integer> {
}
