package com.enesd.myshelfbackend.repository;

import com.enesd.myshelfbackend.model.compositeKeys.UserBookWishlistId;
import com.enesd.myshelfbackend.model.entities.UserBookWishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBookWishlistRepository extends JpaRepository<UserBookWishlist, UserBookWishlistId> {
}
