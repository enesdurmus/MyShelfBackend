package com.enesd.myshelfbackend.repository.jpa;

import com.enesd.myshelfbackend.model.entities.User;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<User> findForUpdateById(UUID id);

    @Query("SELECT user FROM User user " +
            "JOIN FETCH user.roles " +
            "WHERE user.email = (:email)")
    Optional<User> findByEmailWithRoles(@Param("email") String email);
}
