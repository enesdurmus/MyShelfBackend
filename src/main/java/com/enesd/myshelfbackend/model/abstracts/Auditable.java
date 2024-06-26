package com.enesd.myshelfbackend.model.abstracts;

import com.enesd.myshelfbackend.model.interfaces.IAuditable;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@EntityListeners(AuditingEntityListener.class)
@Data
@MappedSuperclass
public abstract class Auditable implements IAuditable {
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    protected Instant createdAt;

    @UpdateTimestamp
    protected Instant updatedAt;
}
