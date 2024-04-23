package com.enesd.myshelfbackend.controller;

import com.enesd.myshelfbackend.model.entities.User;
import com.enesd.myshelfbackend.model.response.GenericResponse;
import com.enesd.myshelfbackend.service.BookService;
import com.enesd.myshelfbackend.service.UserBookReadService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/read_books")
@AllArgsConstructor
public class UserBookReadController {
    private final UserBookReadService userBookReadService;

    @PostMapping("")
    public GenericResponse addBookToRead(@RequestParam int bookId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userBookReadService.addBookToUserReads(user, bookId);
        return GenericResponse.success(null);
    }

    @GetMapping("")
    public GenericResponse getUserReadBooks() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return GenericResponse.success(userBookReadService.getUserReadBooks(user));
    }
}
