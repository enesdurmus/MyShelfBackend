package com.enesd.myshelfbackend.repository;

import com.enesd.myshelfbackend.model.compositeKeys.UserBookReadId;
import com.enesd.myshelfbackend.model.entities.UserBookRead;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserBookReadRepository extends JpaRepository<UserBookRead, UserBookReadId> {
    List<UserBookRead> findAllByIdUserId(UUID userId);
}
