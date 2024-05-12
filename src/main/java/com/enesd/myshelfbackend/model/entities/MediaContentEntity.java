package com.enesd.myshelfbackend.model.entities;

import com.enesd.myshelfbackend.model.abstracts.MediaContent;
import com.enesd.myshelfbackend.model.interfaces.IAuditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "media_contents")
public class MediaContentEntity extends MediaContent implements IAuditable {

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
