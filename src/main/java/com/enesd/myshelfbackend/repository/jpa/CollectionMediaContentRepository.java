package com.enesd.myshelfbackend.repository.jpa;

import com.enesd.myshelfbackend.model.compositeKeys.MediaContentCollectionId;
import com.enesd.myshelfbackend.model.entities.Collection;
import com.enesd.myshelfbackend.model.entities.CollectionMediaContent;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollectionMediaContentRepository extends JpaRepository<CollectionMediaContent, MediaContentCollectionId> {
    @EntityGraph(attributePaths = {"mediaContent"}, type = EntityGraph.EntityGraphType.FETCH)
    List<CollectionMediaContent> findAllByCollection(Collection collection);
}
