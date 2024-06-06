package com.enesd.myshelfbackend.repository.jpa;

import com.enesd.myshelfbackend.model.entities.BookEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface BookEntityRepository extends JpaRepository<BookEntity, Long> {

    List<BookEntity> findAllByUpdatedAtIsGreaterThanEqual(Instant date, Pageable pageable);
}
