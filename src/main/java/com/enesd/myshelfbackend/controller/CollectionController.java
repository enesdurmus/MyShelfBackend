package com.enesd.myshelfbackend.controller;

import com.enesd.myshelfbackend.dto.*;
import com.enesd.myshelfbackend.model.entities.User;
import com.enesd.myshelfbackend.model.request.AddContentToCollectionRequest;
import com.enesd.myshelfbackend.model.request.CreateCollectionRequest;
import com.enesd.myshelfbackend.model.response.GenericResponse;
import com.enesd.myshelfbackend.services.CollectionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/collections")
@AllArgsConstructor
public class CollectionController {
    private final CollectionService collectionService;

    @PostMapping(name = "")
    public ResponseEntity<GenericResponse<CollectionDTO>> createCollection(@RequestBody CreateCollectionRequest createCollectionRequest) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(GenericResponse.success(collectionService.createCollection(user, createCollectionRequest)));
    }

    @GetMapping(name = "")
    public ResponseEntity<GenericResponse<List<CollectionDTO>>> getCollections() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(GenericResponse.success(collectionService.getCollectionsOfUser(user)));
    }

    @PostMapping("/{collectionId}/books")
    public ResponseEntity<GenericResponse<CollectionBookDTO>> addBookToCollection(@PathVariable Long collectionId, @RequestBody AddContentToCollectionRequest addContentToCollectionRequest) {
        return ResponseEntity.ok(GenericResponse.success(collectionService.addBookToCollection(collectionId, addContentToCollectionRequest)));
    }

    @GetMapping("/{collectionId}/books")
    public ResponseEntity<GenericResponse<List<BookDTO>>> getBooksOfCollectionById(@PathVariable Long collectionId) {
        return ResponseEntity.ok(GenericResponse.success(collectionService.getBooksOfCollectionById(collectionId)));
    }

    @PostMapping("/{collectionId}/media_contents")
    public ResponseEntity<GenericResponse<CollectionMediaContentDTO>> addMediaContentToCollection(@PathVariable Long collectionId, @RequestBody AddContentToCollectionRequest addContentToCollectionRequest) {
        return ResponseEntity.ok(GenericResponse.success(collectionService.addMediaContentToCollection(collectionId, addContentToCollectionRequest)));
    }

    @GetMapping("/{collectionId}/media_contents")
    public ResponseEntity<GenericResponse<List<MediaContentDTO>>> getMediaContentsOfCollectionById(@PathVariable Long collectionId) {
        return ResponseEntity.ok(GenericResponse.success(collectionService.getMediaContentsOfCollectionById(collectionId)));
    }
}