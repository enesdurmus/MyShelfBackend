package com.enesd.myshelfbackend.services;

import com.enesd.myshelfbackend.dto.*;
import com.enesd.myshelfbackend.model.entities.*;
import com.enesd.myshelfbackend.model.request.CreateCollectionRequest;
import com.enesd.myshelfbackend.repository.jpa.*;
import com.enesd.myshelfbackend.utils.CustomModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CollectionService {
    private final CollectionRepository collectionRepository;
    private final UserRepository userRepository;
    private final CustomModelMapper modelMapper;

    public CollectionDTO createCollection(User user, CreateCollectionRequest createCollectionRequest) {
        Collection collection = new Collection();
        collection.setName(createCollectionRequest.getName());
        collection.setUser(user);
        collection.setContentType(createCollectionRequest.getContentType());
        collectionRepository.save(collection);
        return modelMapper.map(collection, CollectionDTO.class);
    }

    public List<CollectionDTO> getCollectionsOfUser(User user) {
        List<Collection> collections = collectionRepository.findAllByUser(user);
        return modelMapper.mapAll(collections, CollectionDTO.class);
    }

    public List<CollectionDTO> getPublicCollectionsOfUser(UUID userId) {
        User user = userRepository.getReferenceById(userId);
        List<Collection> collections = collectionRepository.findAllByUserAndIsPublicViewTrue(user);
        return modelMapper.mapAll(collections, CollectionDTO.class);
    }
}