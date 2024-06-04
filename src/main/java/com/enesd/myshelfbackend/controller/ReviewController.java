package com.enesd.myshelfbackend.controller;

import com.enesd.myshelfbackend.model.entities.User;
import com.enesd.myshelfbackend.model.request.CreateReviewRequest;
import com.enesd.myshelfbackend.services.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/reviews")
@AllArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PreAuthorize("hasAuthority('APP_USER') or hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<Void> createReview(@RequestBody @Validated CreateReviewRequest createReviewRequest) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        reviewService.createReview(user, createReviewRequest);
        return ResponseEntity.noContent().build();
    }
}
