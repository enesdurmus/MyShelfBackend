package com.enesd.myshelfbackend.model.entities;

import com.enesd.myshelfbackend.model.compositeKeys.UserBookWishlistId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Data
@Table(name = "user_books_wishlist")
public class UserBookWishlist {

    @EmbeddedId
    private UserBookWishlistId userBookWishlistId;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;
}
