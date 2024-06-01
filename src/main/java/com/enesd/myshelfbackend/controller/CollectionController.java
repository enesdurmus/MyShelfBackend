package com.enesd.myshelfbackend.controller;

import com.enesd.myshelfbackend.dto.CollectionDTO;
import com.enesd.myshelfbackend.model.abstracts.Content;
import com.enesd.myshelfbackend.model.entities.Collection;
import com.enesd.myshelfbackend.model.entities.User;
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

    @PostMapping("/{collectionId}/contents")
    public ResponseEntity<GenericResponse<Collection>> addContentToCollection(@PathVariable Long collectionId, @RequestBody Content content) {
        return ResponseEntity.ok(GenericResponse.success(collectionService.addContentToCollection(collectionId, content)));
    }
}