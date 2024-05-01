package com.enesd.myshelfbackend.repository;

import com.enesd.myshelfbackend.model.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookEntityRepository extends JpaRepository<BookEntity, Integer> {
    List<BookEntity> findByTitleContaining(String searchText);
}
