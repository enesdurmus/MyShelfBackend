package com.enesd.myshelfbackend.controller;

import com.enesd.myshelfbackend.dto.SignInDTO;
import com.enesd.myshelfbackend.model.entities.Book;
import com.enesd.myshelfbackend.model.entities.User;
import com.enesd.myshelfbackend.model.response.GenericResponse;
import com.enesd.myshelfbackend.service.BookService;
import com.enesd.myshelfbackend.service.UserBookReadService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/search")
    public ResponseEntity<GenericResponse<List<Book>>> searchRelatedBooks(@RequestParam String searchText) {
        return ResponseEntity.ok(GenericResponse.success(bookService.searchRelatedBooks(searchText)));
    }
}
