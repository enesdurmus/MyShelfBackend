package com.enesd.myshelfbackend.repository.jpa;

import com.enesd.myshelfbackend.model.entities.RefreshToken;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    @EntityGraph(attributePaths = {"user", "user.roles"}, type = EntityGraph.EntityGraphType.FETCH)
    Optional<RefreshToken> findByRefreshToken(@Param("refreshToken") String refreshToken);

    @Query(value = "INSERT INTO refresh_tokens (user_id, refresh_token, expired_at)\n" +
            "VALUES (?1, ?2, ?3)\n" +
            "ON CONFLICT (user_id)\n" +
            "DO UPDATE SET refresh_token = ?2, expired_at = ?3\n" +
            "RETURNING *;", nativeQuery = true)
    RefreshToken saveRefreshToken(UUID userId, String token, Instant instant);
}
