package com.enesd.myshelfbackend.controller;

import com.enesd.myshelfbackend.model.documents.BookDocument;
import com.enesd.myshelfbackend.model.entities.BookEntity;
import com.enesd.myshelfbackend.model.response.GenericResponse;
import com.enesd.myshelfbackend.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/search")
    public ResponseEntity<GenericResponse<List<BookDocument>>> searchRelatedBooks(@RequestParam String searchTerm) {
        return ResponseEntity.ok(GenericResponse.success(bookService.searchRelatedBooks(searchTerm)));
    }

    @GetMapping("")
    public ResponseEntity<GenericResponse<BookEntity>> findBookById(@RequestParam(required = true) int bookId) {
        return ResponseEntity.ok(GenericResponse.success(bookService.findBookById(bookId)));
    }
}
