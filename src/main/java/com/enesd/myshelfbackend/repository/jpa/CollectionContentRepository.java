package com.enesd.myshelfbackend.repository.jpa;

import com.enesd.myshelfbackend.model.compositeKeys.CollectionContentId;
import com.enesd.myshelfbackend.model.entities.CollectionContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionContentRepository extends JpaRepository<CollectionContent, CollectionContentId> {
}
