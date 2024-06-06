package com.enesd.myshelfbackend.repository.jpa;

import com.enesd.myshelfbackend.model.entities.SyncStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SyncStatusRepository extends JpaRepository<SyncStatus, Long> {
    Optional<SyncStatus> findByEntityName(String entityName);
}
