package com.enesd.myshelfbackend.controller;

import com.enesd.myshelfbackend.enums.ContentType;
import com.enesd.myshelfbackend.model.request.CreateWishlistRequest;
import com.enesd.myshelfbackend.model.response.GenericResponse;
import com.enesd.myshelfbackend.services.WishlistService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/wishlists")
@AllArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;

    @PostMapping("")
    public ResponseEntity<GenericResponse<Void>> createWishlist(@Validated @RequestBody CreateWishlistRequest createWishlistRequest) {
        return null;
    }

    @GetMapping("")
    public ResponseEntity<GenericResponse<Void>> getWishlists(@RequestParam ContentType contentType) {
        return null;
    }

    @DeleteMapping("")
    public ResponseEntity<GenericResponse<Void>> deleteWishlist(@RequestParam Integer contentId, @RequestParam ContentType contentType) {
        return null;
    }
}
