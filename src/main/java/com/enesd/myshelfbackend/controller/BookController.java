package com.enesd.myshelfbackend.controller;

import com.enesd.myshelfbackend.model.entities.User;
import com.enesd.myshelfbackend.model.response.GenericResponse;
import com.enesd.myshelfbackend.service.BookService;
import com.enesd.myshelfbackend.service.UserBookReadService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;
    private final UserBookReadService userBookReadService;

    @GetMapping("/search")
    public GenericResponse searchRelatedBooks(@RequestParam String searchText) {
        return GenericResponse.success(bookService.searchRelatedBooks(searchText));
    }

    @PostMapping("/add/wishlist")
    public GenericResponse addBookToWishlist(@RequestParam int bookId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return GenericResponse.success(null);
    }

    @PostMapping("/add/read")
    public GenericResponse addBookToRead(@RequestParam int bookId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userBookReadService.addBookToUserReads(user, bookId);
        return GenericResponse.success(null);
    }
}
