package com.enesd.myshelfbackend.repository.jpa;

import com.enesd.myshelfbackend.enums.ContentType;
import com.enesd.myshelfbackend.model.entities.User;
import com.enesd.myshelfbackend.model.entities.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    List<Wishlist> findAllByUserAndContentType(User userId, ContentType contentType);

    @Modifying
    @Query("DELETE FROM Wishlist WHERE id = :id")
    void deleteById(@Param("id") Long id);
}
