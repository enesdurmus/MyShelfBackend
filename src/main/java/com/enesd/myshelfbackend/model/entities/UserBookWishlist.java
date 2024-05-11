package com.enesd.myshelfbackend.model.entities;

import com.enesd.myshelfbackend.model.abstracts.Auditable;
import com.enesd.myshelfbackend.model.compositeKeys.UserBookWishlistId;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@IdClass(UserBookWishlistId.class)
@Table(name = "user_books_wishlist")
public class UserBookWishlist extends Auditable {

    @Id
    @Column(name = "user_id")
    private UUID userId;

    @Id
    @Column(name = "book_id")
    private int bookId;
}
