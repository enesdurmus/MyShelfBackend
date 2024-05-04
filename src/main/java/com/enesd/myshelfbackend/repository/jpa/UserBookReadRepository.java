package com.enesd.myshelfbackend.repository.jpa;

import com.enesd.myshelfbackend.model.compositeKeys.UserBookReadId;
import com.enesd.myshelfbackend.model.entities.UserBookRead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

public interface UserBookReadRepository extends JpaRepository<UserBookRead, UserBookReadId> {
    List<UserBookRead> findAllByUserId(UUID userId);
}
