package com.enesd.myshelfbackend.repository.jpa;

import com.enesd.myshelfbackend.model.entities.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccessTokenRepository extends JpaRepository<AccessToken, UUID> {
}
