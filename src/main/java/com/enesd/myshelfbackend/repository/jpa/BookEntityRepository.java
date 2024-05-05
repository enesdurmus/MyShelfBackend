package com.enesd.myshelfbackend.repository.jpa;

import com.enesd.myshelfbackend.model.entities.BookEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookEntityRepository extends JpaRepository<BookEntity, Integer> {
    List<BookEntity> findByIdNotIn(List<Integer> bookIds, Pageable pageable);
}
