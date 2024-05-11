package com.enesd.myshelfbackend.model.base;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@EntityListeners(AuditingEntityListener.class)
@Data
@MappedSuperclass
public abstract class Auditable {
    @CreatedBy
    protected String createdBy;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    protected Instant createdAt;

    @LastModifiedBy
    protected String updatedBy;

    @UpdateTimestamp
    protected Instant updatedAt;
}
