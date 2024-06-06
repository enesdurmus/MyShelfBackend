package com.enesd.myshelfbackend.repository.jpa;

import com.enesd.myshelfbackend.model.entities.Collection;
import com.enesd.myshelfbackend.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollectionRepository extends JpaRepository<Collection, Long> {
    List<Collection> findAllByUser(User user);

    List<Collection> findAllByUserAndIsPublicViewTrue(User user);
}
