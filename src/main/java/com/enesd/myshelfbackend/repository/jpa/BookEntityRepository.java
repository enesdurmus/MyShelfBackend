package com.enesd.myshelfbackend.repository.jpa;

import com.enesd.myshelfbackend.model.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BookEntityRepository extends JpaRepository<BookEntity, Integer> {
    List<BookEntity> findByTitleContaining(String searchText);
}
