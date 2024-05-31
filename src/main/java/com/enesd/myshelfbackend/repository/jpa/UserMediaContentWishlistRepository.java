package com.enesd.myshelfbackend.repository.jpa;

import com.enesd.myshelfbackend.model.compositeKeys.UserMediaContentWishlistId;
import com.enesd.myshelfbackend.model.entities.User;
import com.enesd.myshelfbackend.model.entities.UserMediaContentWishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserMediaContentWishlistRepository extends JpaRepository<UserMediaContentWishlist, UserMediaContentWishlistId> {
    List<UserMediaContentWishlist> findAllByUser(User user);
}
