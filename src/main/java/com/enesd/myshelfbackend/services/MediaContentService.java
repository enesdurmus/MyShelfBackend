package com.enesd.myshelfbackend.services;

import com.enesd.myshelfbackend.model.documents.MediaContentDocument;
import com.enesd.myshelfbackend.model.entities.MediaContentEntity;
import com.enesd.myshelfbackend.repository.elasticsearch.MediaContentDocumentRepository;
import com.enesd.myshelfbackend.repository.jpa.MediaContentEntityRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MediaContentService {

    private final MediaContentEntityRepository mediaContentEntityRepository;
    private final MediaContentDocumentRepository mediaContentDocumentRepository;

    public List<MediaContentDocument> searchRelatedMediaContents(String searchTerm) {
        return mediaContentDocumentRepository.findWithSearchTerm(searchTerm);
    }

    public MediaContentEntity findMediaContentById(int mediaContentId) {
        return mediaContentEntityRepository.findById(mediaContentId).orElseThrow(EntityNotFoundException::new);
    }
}
