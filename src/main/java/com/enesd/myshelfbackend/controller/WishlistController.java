package com.enesd.myshelfbackend.controller;

import com.enesd.myshelfbackend.enums.ContentType;
import com.enesd.myshelfbackend.model.request.CreateWishlistRequest;
import com.enesd.myshelfbackend.model.response.GenericResponse;
import com.enesd.myshelfbackend.services.WishlistService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/wishlists")
@AllArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;

    @PreAuthorize("hasAuthority('APP_USER') or hasAuthority('ADMIN')")
    @PostMapping("")
    public ResponseEntity<GenericResponse<Void>> createWishlist(@Validated @RequestBody CreateWishlistRequest createWishlistRequest) {
        return null;
    }

    @PreAuthorize("hasAuthority('APP_USER') or hasAuthority('ADMIN')")
    @GetMapping("")
    public ResponseEntity<GenericResponse<Void>> getWishlists(@RequestParam ContentType contentType) {
        return null;
    }

    @PreAuthorize("hasAuthority('APP_USER') or hasAuthority('ADMIN')")
    @DeleteMapping("")
    public ResponseEntity<GenericResponse<Void>> deleteWishlist(@RequestParam Integer contentId, @RequestParam ContentType contentType) {
        return null;
    }
}
