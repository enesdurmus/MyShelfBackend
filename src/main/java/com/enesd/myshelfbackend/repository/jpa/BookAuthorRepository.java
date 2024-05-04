package com.enesd.myshelfbackend.repository.jpa;

import com.enesd.myshelfbackend.model.entities.BookAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface BookAuthorRepository extends JpaRepository<BookAuthor, Integer> {
}
