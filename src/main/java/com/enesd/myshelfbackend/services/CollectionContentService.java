package com.enesd.myshelfbackend.services;

import com.enesd.myshelfbackend.model.abstracts.Content;
import com.enesd.myshelfbackend.model.entities.Collection;
import com.enesd.myshelfbackend.model.entities.CollectionContent;
import com.enesd.myshelfbackend.model.request.AddContentToCollectionRequest;
import com.enesd.myshelfbackend.repository.jpa.CollectionContentRepository;
import com.enesd.myshelfbackend.repository.jpa.CollectionRepository;
import com.enesd.myshelfbackend.strategies.context.ContentRetrievalStrategyContext;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CollectionContentService {
    private final CollectionRepository collectionRepository;
    private final CollectionContentRepository collectionContentRepository;
    private final ContentRetrievalStrategyContext contentRetrievalStrategyContext;

    public void addContentToCollection(AddContentToCollectionRequest addContentToCollectionRequest) {
        Collection collection = collectionRepository.getReferenceById(addContentToCollectionRequest.getCollectionId());

        CollectionContent collectionContent = new CollectionContent();
        collectionContent.setCollection(collection);
        collectionContent.setContent(addContentToCollectionRequest.getContentId());

        collectionContentRepository.save(collectionContent);
    }

    public List<Content> getContentsOfCollection(Long collectionId) {
        Collection collection = collectionRepository.findById(collectionId).orElseThrow(EntityNotFoundException::new);
        return contentRetrievalStrategyContext.getContents(collection.getContentType(), collectionId);
    }
}