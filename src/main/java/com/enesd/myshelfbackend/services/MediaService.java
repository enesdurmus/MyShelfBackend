package com.enesd.myshelfbackend.services;

import com.enesd.myshelfbackend.model.documents.MediaContentDocument;
import com.enesd.myshelfbackend.model.entities.MediaEntity;
import com.enesd.myshelfbackend.model.interfaces.ISearchable;
import com.enesd.myshelfbackend.repository.elasticsearch.MediaContentDocumentRepository;
import com.enesd.myshelfbackend.repository.jpa.MediaContentEntityRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class MediaService implements ISearchable {

    private final MediaContentEntityRepository mediaContentEntityRepository;
    private final MediaContentDocumentRepository mediaContentDocumentRepository;

    public List<MediaContentDocument> searchRelatedMediaContents(String searchTerm) {
        return mediaContentDocumentRepository.findWithSearchTerm(searchTerm);
    }

    public MediaEntity findMediaContentById(Long mediaContentId) {
        return mediaContentEntityRepository.findById(mediaContentId).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Object> searchAllRelevant(String searchTerm) {
        return Collections.singletonList(searchRelatedMediaContents(searchTerm));
    }
}
