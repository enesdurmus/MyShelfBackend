package com.enesd.myshelfbackend.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Table(name = "sync_statuses")
@Data
public class SyncStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "entity_name")
    private String entityName;

    @Column(name = "last_sync_time")
    private Instant lastSyncTime;

    @Version
    private Long version;
}
