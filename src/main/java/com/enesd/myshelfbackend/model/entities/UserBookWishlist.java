package com.enesd.myshelfbackend.model.entities;

import com.enesd.myshelfbackend.model.abstracts.Auditable;

import com.enesd.myshelfbackend.model.compositeKeys.UserBookWishlistId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

@Entity
@Data
@Table(name = "user_books_wishlists")
@IdClass(UserBookWishlistId.class)
@AllArgsConstructor
@NoArgsConstructor
public class UserBookWishlist extends Auditable implements Persistable<UserBookWishlistId> {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private BookEntity book;

    @Override
    public UserBookWishlistId getId() {
        return new UserBookWishlistId(user.getId(), book.getId());
    }

    @Override
    public boolean isNew() {
        return true;
    }
}
