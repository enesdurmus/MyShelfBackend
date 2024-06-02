package com.enesd.myshelfbackend.repository.jpa;

import com.enesd.myshelfbackend.model.compositeKeys.MediaContentCollectionId;
import com.enesd.myshelfbackend.model.entities.CollectionMediaContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionMediaContentRepository extends JpaRepository<CollectionMediaContent, MediaContentCollectionId> {
}
