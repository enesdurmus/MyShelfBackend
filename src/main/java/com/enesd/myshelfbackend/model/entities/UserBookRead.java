package com.enesd.myshelfbackend.model.entities;

import com.enesd.myshelfbackend.model.abstracts.Auditable;
import com.enesd.myshelfbackend.model.compositeKeys.UserBookReadId;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.domain.Persistable;

import java.util.UUID;

@Entity
@Data
@IdClass(UserBookReadId.class)
@Table(name = "user_books_read")
public class UserBookRead extends Auditable implements Persistable<UserBookReadId> {

    @Id
    @Column(name = "user_id")
    private UUID userId;

    @Id
    @Column(name = "book_id")
    private int bookId;

    @Override
    public UserBookReadId getId() {
        return new UserBookReadId(userId, bookId);
    }

    @Override
    public boolean isNew() {
        return true;
    }
}
