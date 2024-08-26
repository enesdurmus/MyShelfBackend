package com.enesd.myshelfbackend.controller;

import com.enesd.myshelfbackend.model.abstracts.Content;
import com.enesd.myshelfbackend.model.request.AddContentToCollectionRequest;
import com.enesd.myshelfbackend.model.response.GenericResponse;
import com.enesd.myshelfbackend.services.CollectionContentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/collection-contents")
@AllArgsConstructor
public class CollectionContentController {
    private final CollectionContentService collectionContentService;

    @PostMapping()
    public ResponseEntity<GenericResponse<Void>> addContentToCollection(@Validated @RequestBody AddContentToCollectionRequest addContentToCollectionRequest) {
        collectionContentService.addContentToCollection(addContentToCollectionRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(GenericResponse.empty());
    }

    @GetMapping(value = "/{collection_id}")
    public ResponseEntity<GenericResponse<List<Content>>> getContentsOfCollection(@PathVariable(value = "collection_id") Long collectionId) {
        return ResponseEntity.ok(GenericResponse.success(collectionContentService.getContentsOfCollection(collectionId)));
    }
}
