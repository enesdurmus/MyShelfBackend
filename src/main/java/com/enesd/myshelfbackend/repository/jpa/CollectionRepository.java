package com.enesd.myshelfbackend.repository.jpa;

import com.enesd.myshelfbackend.model.entities.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionRepository extends JpaRepository<Collection, Long> {
}
