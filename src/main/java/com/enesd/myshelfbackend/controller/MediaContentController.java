package com.enesd.myshelfbackend.controller;

import com.enesd.myshelfbackend.model.documents.MediaContentDocument;
import com.enesd.myshelfbackend.model.entities.MediaContentEntity;
import com.enesd.myshelfbackend.model.response.GenericResponse;
import com.enesd.myshelfbackend.repository.elasticsearch.MediaContentDocumentRepository;
import com.enesd.myshelfbackend.repository.jpa.MediaContentEntityRepository;
import com.enesd.myshelfbackend.service.MediaContentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/media_contents")
@AllArgsConstructor
public class MediaContentController {

    private final MediaContentService mediaContentService;

    @GetMapping("/search")
    public ResponseEntity<GenericResponse<List<MediaContentDocument>>> searchRelatedMediaContents(@RequestParam String searchTerm) {
        return ResponseEntity.ok(GenericResponse.success(mediaContentService.searchRelatedMediaContents(searchTerm)));
    }

    @GetMapping("")
    public ResponseEntity<GenericResponse<MediaContentEntity>> findMediaContentById(@RequestParam(required = true) int mediaContentId) {
        return ResponseEntity.ok(GenericResponse.success(mediaContentService.findMediaContentById(mediaContentId)));
    }
}
