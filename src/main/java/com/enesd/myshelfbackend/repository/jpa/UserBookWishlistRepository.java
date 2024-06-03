package com.enesd.myshelfbackend.repository.jpa;

import com.enesd.myshelfbackend.model.compositeKeys.UserBookWishlistId;
import com.enesd.myshelfbackend.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserBookWishlistRepository extends JpaRepository<UserBookWishlist, UserBookWishlistId> {

    List<UserBookWishlist> findAllByUser(User user);
}
