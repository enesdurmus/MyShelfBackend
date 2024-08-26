package com.enesd.myshelfbackend.strategies.context;

import com.enesd.myshelfbackend.enums.ContentType;
import com.enesd.myshelfbackend.model.abstracts.Content;
import com.enesd.myshelfbackend.strategies.interfaces.IContentRetrievalStrategy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class ContentRetrievalStrategyContext {
    private final Map<ContentType, IContentRetrievalStrategy> strategies;

    public List<Content> getContents(ContentType contentType, Long collectionId) {
        return strategies.get(contentType).retrieveContentsByCollection(collectionId);
    }
}
