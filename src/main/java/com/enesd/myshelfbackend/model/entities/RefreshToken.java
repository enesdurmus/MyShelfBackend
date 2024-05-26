package com.enesd.myshelfbackend.model.entities;

import com.enesd.myshelfbackend.model.abstracts.Auditable;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.domain.Persistable;

import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@Table(name = "refresh_tokens")
public class RefreshToken extends Auditable implements Persistable<UUID> {
    @Id
    @Column(name = "user_id")
    private UUID userId;

    @OneToOne
    @JoinColumn(name = "user_id")
    @MapsId
    private User user;

    @Column(name = "refresh_token", unique = true)
    private String refreshToken;

    @Column(name = "expired_at")
    private Instant expiredAt;

    @Override
    public UUID getId() {
        return userId;
    }

    @Override
    public boolean isNew() {
        return true;
    }
}
