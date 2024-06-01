package com.enesd.myshelfbackend.services;

import com.enesd.myshelfbackend.model.abstracts.Content;
import com.enesd.myshelfbackend.model.entities.Collection;
import com.enesd.myshelfbackend.model.entities.User;
import com.enesd.myshelfbackend.model.request.CreateCollectionRequest;
import com.enesd.myshelfbackend.repository.jpa.CollectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CollectionService {
    private final CollectionRepository collectionRepository;

    public Collection createCollection(User user, CreateCollectionRequest createCollectionRequest) {
        Collection collection = new Collection();
        collection.setName(createCollectionRequest.getName());
        collection.setUser(user);
        return collectionRepository.save(collection);
    }

    public List<Collection> getCollections() {
        return collectionRepository.findAll();
    }

    public Collection addContentToCollection(Long collectionId, Content content) {
        Collection collection = collectionRepository.findById(collectionId).orElseThrow();
        collection.getContents().add(content);
        return collectionRepository.save(collection);
    }
}