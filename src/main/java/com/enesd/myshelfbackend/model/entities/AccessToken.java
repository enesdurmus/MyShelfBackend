package com.enesd.myshelfbackend.model.entities;

import com.enesd.myshelfbackend.model.abstracts.Auditable;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "access_tokens")
public class AccessToken extends Auditable {

    @Id
    @Column(name = "user_id")
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    @Column(name = "token", unique = true, columnDefinition = "TEXT")
    private String token;
}
