package com.enesd.myshelfbackend.services;

import com.enesd.myshelfbackend.dto.CollectionMediaContentDTO;
import com.enesd.myshelfbackend.dto.MediaContentDTO;
import com.enesd.myshelfbackend.model.entities.Collection;
import com.enesd.myshelfbackend.model.entities.CollectionMediaContent;
import com.enesd.myshelfbackend.model.entities.MediaContentEntity;
import com.enesd.myshelfbackend.model.request.AddContentToCollectionRequest;
import com.enesd.myshelfbackend.repository.jpa.*;
import com.enesd.myshelfbackend.utils.CustomModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CollectionMediaContentService {
    private final CollectionRepository collectionRepository;
    private final CollectionMediaContentRepository collectionMediaContentRepository;
    private final MediaContentEntityRepository mediaContentEntityRepository;
    private final CustomModelMapper modelMapper;

    public CollectionMediaContentDTO addMediaContentToCollection(Long collectionId, AddContentToCollectionRequest addContentToCollectionRequest) {
        Collection collection = collectionRepository.getReferenceById(collectionId);
        MediaContentEntity mediaContentEntity = mediaContentEntityRepository.getReferenceById(addContentToCollectionRequest.getContentId());
        CollectionMediaContent collectionMediaContent = new CollectionMediaContent(collection, mediaContentEntity);
        collectionMediaContentRepository.save(collectionMediaContent);
        return modelMapper.map(collectionMediaContent, CollectionMediaContentDTO.class);
    }

    public List<MediaContentDTO> getMediaContentsOfCollectionById(Long collectionId) {
        Collection collection = collectionRepository.getReferenceById(collectionId);
        List<CollectionMediaContent> collectionMediaContents = collectionMediaContentRepository.findAllByCollection(collection);
        return collectionMediaContents
                .stream()
                .map(collectionMediaContent -> modelMapper.map(collectionMediaContent.getMediaContent(), MediaContentDTO.class))
                .collect(Collectors.toList());
    }
}