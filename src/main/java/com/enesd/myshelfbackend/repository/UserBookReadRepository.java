package com.enesd.myshelfbackend.repository;

import com.enesd.myshelfbackend.model.compositeKeys.UserBookReadId;
import com.enesd.myshelfbackend.model.entities.UserBookRead;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBookReadRepository extends JpaRepository<UserBookRead, UserBookReadId> {
}
