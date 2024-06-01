package com.enesd.myshelfbackend.controller;

import com.enesd.myshelfbackend.dto.UserBookWishlistDTO;
import com.enesd.myshelfbackend.dto.UserMediaContentWishlistDTO;
import com.enesd.myshelfbackend.model.entities.User;
import com.enesd.myshelfbackend.model.response.GenericResponse;
import com.enesd.myshelfbackend.services.WishlistService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/wishlists")
@AllArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;

    @PreAuthorize("hasAuthority('APP_USER') or hasAuthority('ADMIN')")
    @PostMapping("/books/{bookId}")
    public ResponseEntity<Void> addBookToWishlist(@PathVariable Long bookId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        wishlistService.addBookToWishlist(user, bookId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PreAuthorize("hasAuthority('APP_USER') or hasAuthority('ADMIN')")
    @PostMapping("/media_contents/{mediaContentId}")
    public ResponseEntity<Void> addMediaContentToWishlist(@PathVariable Long mediaContentId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        wishlistService.addMediaContentToWishlist(user, mediaContentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PreAuthorize("hasAuthority('APP_USER') or hasAuthority('ADMIN')")
    @GetMapping("/books")
    public ResponseEntity<GenericResponse<List<UserBookWishlistDTO>>> getBookWishlists() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(GenericResponse.success(wishlistService.getBookWishlists(user)));
    }

    @PreAuthorize("hasAuthority('APP_USER') or hasAuthority('ADMIN')")
    @GetMapping("/media_contents")
    public ResponseEntity<GenericResponse<List<UserMediaContentWishlistDTO>>> getMediaContentWishlists() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(GenericResponse.success(wishlistService.getMediaContentWishlists(user)));
    }

//    @PreAuthorize("hasAuthority('APP_USER') or hasAuthority('ADMIN')")
//    @DeleteMapping("")
//    public ResponseEntity<Void> deleteWishlist(@RequestParam Long id) {
//        wishlistService.deleteWishlist(id);
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//    }
}
