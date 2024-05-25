package com.enesd.myshelfbackend.model.entities;

import com.enesd.myshelfbackend.model.abstracts.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "api_keys")
@AllArgsConstructor
public class ApiKey extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long apiKeyId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "api_key", nullable = false, unique = true)
    private String apiKey;

    @Column(nullable = false)
    private Boolean isActive = true;
}
