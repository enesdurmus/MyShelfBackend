package com.enesd.myshelfbackend.repository.jpa;

import com.enesd.myshelfbackend.model.entities.MediaContentEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MediaContentEntityRepository extends JpaRepository<MediaContentEntity, Long> {
    List<MediaContentEntity> findByIdNotIn(List<Long> mediaContentIds, Pageable pageable);
}