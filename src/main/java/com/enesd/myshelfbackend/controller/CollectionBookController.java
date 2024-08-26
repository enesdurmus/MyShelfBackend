package com.enesd.myshelfbackend.controller;

import com.enesd.myshelfbackend.dto.BookDTO;
import com.enesd.myshelfbackend.dto.CollectionBookDTO;
import com.enesd.myshelfbackend.model.request.AddContentToCollectionRequest;
import com.enesd.myshelfbackend.model.response.GenericResponse;
import com.enesd.myshelfbackend.services.CollectionBookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/collections/books")
@AllArgsConstructor
public class CollectionBookController {
    private final CollectionBookService collectionBookService;

    @PostMapping("/{collectionId}")
    public ResponseEntity<GenericResponse<CollectionBookDTO>> addBookToCollection(@PathVariable Long collectionId, @RequestBody AddContentToCollectionRequest addContentToCollectionRequest) {
        return ResponseEntity.ok(GenericResponse.success(collectionBookService.addBookToCollection(collectionId, addContentToCollectionRequest)));
    }

    @GetMapping("/{collectionId}")
    public ResponseEntity<GenericResponse<List<BookDTO>>> getBooksOfCollectionById(@PathVariable Long collectionId) {
        return ResponseEntity.ok(GenericResponse.success(collectionBookService.getBooksOfCollectionById(collectionId)));
    }
}