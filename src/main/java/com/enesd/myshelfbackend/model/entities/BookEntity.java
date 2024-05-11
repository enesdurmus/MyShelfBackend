package com.enesd.myshelfbackend.model.entities;

import com.enesd.myshelfbackend.model.abstracts.Book;
import com.enesd.myshelfbackend.model.interfaces.IAuditable;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "books")
@Data
public class BookEntity extends Book implements IAuditable {

    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Instant updatedAt;

    @Override
    public void setCreatedAt(Instant instant) {
        createdAt = instant;
    }

    @Override
    public Instant getCreatedAt() {
        return createdAt;
    }

    @Override
    public void setUpdatedAt(Instant instant) {
        updatedAt = instant;
    }

    @Override
    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
