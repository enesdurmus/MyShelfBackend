package com.enesd.myshelfbackend.services;

import com.enesd.myshelfbackend.dto.CollectionDTO;
import com.enesd.myshelfbackend.model.abstracts.Content;
import com.enesd.myshelfbackend.model.entities.Collection;
import com.enesd.myshelfbackend.model.entities.User;
import com.enesd.myshelfbackend.model.request.CreateCollectionRequest;
import com.enesd.myshelfbackend.repository.jpa.CollectionRepository;
import com.enesd.myshelfbackend.utils.CustomModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CollectionService {
    private final CollectionRepository collectionRepository;
    private final CustomModelMapper modelMapper;

    public CollectionDTO createCollection(User user, CreateCollectionRequest createCollectionRequest) {
        Collection collection = new Collection();
        collection.setName(createCollectionRequest.getName());
        collection.setUser(user);
        collectionRepository.save(collection);
        return modelMapper.map(collection, CollectionDTO.class);
    }

    public List<CollectionDTO> getCollectionsOfUser(User user) {
        List<Collection> collections = collectionRepository.findAllByUser(user);
        return modelMapper.mapAll(collections, CollectionDTO.class);
    }

    public Collection addContentToCollection(Long collectionId, Content content) {
        Collection collection = collectionRepository.findById(collectionId).orElseThrow();
        collection.getContents().add(content);
        return collectionRepository.save(collection);
    }
}