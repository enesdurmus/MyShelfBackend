package com.enesd.myshelfbackend.controller;

import com.enesd.myshelfbackend.dto.WishlistDTO;
import com.enesd.myshelfbackend.enums.ContentType;
import com.enesd.myshelfbackend.model.entities.User;
import com.enesd.myshelfbackend.model.request.CreateWishlistRequest;
import com.enesd.myshelfbackend.model.response.GenericResponse;
import com.enesd.myshelfbackend.services.WishlistService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/wishlists")
@AllArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;

    @PreAuthorize("hasAuthority('APP_USER') or hasAuthority('ADMIN')")
    @PostMapping("")
    public ResponseEntity<GenericResponse<WishlistDTO>> createWishlist(@Validated @RequestBody CreateWishlistRequest createWishlistRequest) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(GenericResponse.success(wishlistService.createWishlist(user, createWishlistRequest)));
    }

    @PreAuthorize("hasAuthority('APP_USER') or hasAuthority('ADMIN')")
    @GetMapping("")
    public ResponseEntity<GenericResponse<List<WishlistDTO>>> getWishlists(@RequestParam ContentType contentType) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(GenericResponse.success(wishlistService.getWishlists(user, contentType)));
    }

    @PreAuthorize("hasAuthority('APP_USER') or hasAuthority('ADMIN')")
    @DeleteMapping("")
    public ResponseEntity<Void> deleteWishlist(@RequestParam Long id) {
        wishlistService.deleteWishlist(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
