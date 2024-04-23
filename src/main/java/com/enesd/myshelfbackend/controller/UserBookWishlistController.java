package com.enesd.myshelfbackend.controller;

import com.enesd.myshelfbackend.model.entities.User;
import com.enesd.myshelfbackend.model.entities.UserBookRead;
import com.enesd.myshelfbackend.model.entities.UserBookWishlist;
import com.enesd.myshelfbackend.model.response.GenericResponse;
import com.enesd.myshelfbackend.service.UserBookWishlistService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/api/v1/wishlist_books")
@AllArgsConstructor
public class UserBookWishlistController {

    private final UserBookWishlistService userBookWishlistService;

    @PostMapping("")
    public ResponseEntity<Void> addBookToRead(@RequestParam int bookId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userBookWishlistService.addBookToUserWishlist(user, bookId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("")
    public ResponseEntity<GenericResponse<List<UserBookWishlist>>> getUserReadBooks() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(GenericResponse.success(userBookWishlistService.getUserReadBooks(user)));
    }
}
