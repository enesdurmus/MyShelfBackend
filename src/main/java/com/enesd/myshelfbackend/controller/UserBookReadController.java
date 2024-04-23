package com.enesd.myshelfbackend.controller;

import com.enesd.myshelfbackend.model.entities.User;
import com.enesd.myshelfbackend.model.entities.UserBookRead;
import com.enesd.myshelfbackend.model.response.GenericResponse;
import com.enesd.myshelfbackend.service.BookService;
import com.enesd.myshelfbackend.service.UserBookReadService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/read_books")
@AllArgsConstructor
public class UserBookReadController {
    private final UserBookReadService userBookReadService;

    @PostMapping("")
    public ResponseEntity<Void> addBookToRead(@RequestParam int bookId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userBookReadService.addBookToUserReads(user, bookId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("")
    public ResponseEntity<GenericResponse<List<UserBookRead>>> getUserReadBooks() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(GenericResponse.success(userBookReadService.getUserReadBooks(user)));
    }
}
