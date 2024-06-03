package com.enesd.myshelfbackend.repository.jpa;

import com.enesd.myshelfbackend.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);

    @Query("SELECT user FROM User user " +
            "JOIN FETCH user.roles " +
            "WHERE user.username = (:username)")
    Optional<User> findByUsernameWithRoles(@Param("username") String username);

    @Modifying
    @Query("UPDATE User u " +
            "SET u.displayName = :displayName " +
            "WHERE u.id = :userId")
    void updateDisplayName(@Param("userId") UUID userId, @Param("displayName") String displayName);
}
