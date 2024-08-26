package com.enesd.myshelfbackend.strategies.impl.contentRetrieveStrategies;

import com.enesd.myshelfbackend.enums.ContentType;
import com.enesd.myshelfbackend.model.abstracts.Content;
import com.enesd.myshelfbackend.repository.jpa.CollectionContentRepository;
import com.enesd.myshelfbackend.strategies.interfaces.IContentRetrievalStrategy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BookRetrievalStrategy implements IContentRetrievalStrategy {
    private final CollectionContentRepository collectionContentRepository;

    @Override
    public List<Content> retrieveContentsByCollection(Long collectionId) {
        return new ArrayList<Content>(collectionContentRepository.findAllBooksByCollectionId(collectionId));
    }

    @Override
    public ContentType getContentType() {
        return ContentType.BOOK;
    }
}
