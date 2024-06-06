package com.enesd.myshelfbackend.controller;

import com.enesd.myshelfbackend.dto.CollectionMediaContentDTO;
import com.enesd.myshelfbackend.dto.MediaContentDTO;
import com.enesd.myshelfbackend.model.request.AddContentToCollectionRequest;
import com.enesd.myshelfbackend.model.response.GenericResponse;
import com.enesd.myshelfbackend.services.CollectionMediaContentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/collections/media_contents")
@AllArgsConstructor
public class CollectionMediaContentController {
    private final CollectionMediaContentService collectionMediaContentService;

    @PreAuthorize("hasAuthority('APP_USER') or hasAuthority('ADMIN')")
    @PostMapping("/{collectionId}")
    public ResponseEntity<GenericResponse<CollectionMediaContentDTO>> addMediaContentToCollection(@PathVariable Long collectionId, @RequestBody AddContentToCollectionRequest addContentToCollectionRequest) {
        return ResponseEntity.ok(GenericResponse.success(collectionMediaContentService.addMediaContentToCollection(collectionId, addContentToCollectionRequest)));
    }

    @PreAuthorize("hasAuthority('APP_USER') or hasAuthority('ADMIN')")
    @GetMapping("/{collectionId}")
    public ResponseEntity<GenericResponse<List<MediaContentDTO>>> getMediaContentsOfCollectionById(@PathVariable Long collectionId) {
        return ResponseEntity.ok(GenericResponse.success(collectionMediaContentService.getMediaContentsOfCollectionById(collectionId)));
    }
}