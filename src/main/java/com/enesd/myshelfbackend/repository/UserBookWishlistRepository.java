package com.enesd.myshelfbackend.repository;

import com.enesd.myshelfbackend.model.compositeKeys.UserBookWishlistId;
import com.enesd.myshelfbackend.model.entities.UserBookWishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserBookWishlistRepository extends JpaRepository<UserBookWishlist, UserBookWishlistId> {

    List<UserBookWishlist> findAllByIdUserId(UUID userId);
}
