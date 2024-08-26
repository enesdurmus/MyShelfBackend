package com.enesd.myshelfbackend.strategies.interfaces;

import com.enesd.myshelfbackend.enums.ContentType;
import com.enesd.myshelfbackend.model.abstracts.Content;

import java.util.List;

public interface IContentRetrievalStrategy {
    List<Content> retrieveContentsByCollection(Long collectionId);

    ContentType getContentType();
}
