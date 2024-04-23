package com.enesd.myshelfbackend.controller;

import com.enesd.myshelfbackend.model.response.GenericResponse;
import com.enesd.myshelfbackend.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/search")
    public GenericResponse searchRelatedBooks(@RequestParam String searchText) {
        return GenericResponse.success(bookService.searchRelatedBooks(searchText));
    }
}
