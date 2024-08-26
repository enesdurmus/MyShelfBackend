package com.enesd.myshelfbackend.controller;

import com.enesd.myshelfbackend.dto.ReviewDTO;
import com.enesd.myshelfbackend.enums.ContentType;
import com.enesd.myshelfbackend.model.entities.User;
import com.enesd.myshelfbackend.model.request.CreateReviewRequest;
import com.enesd.myshelfbackend.model.response.GenericResponse;
import com.enesd.myshelfbackend.services.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/reviews")
@AllArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Void> createReview(@RequestBody @Validated CreateReviewRequest createReviewRequest) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        reviewService.createReview(user, createReviewRequest);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{contentType}/{contentId}")
    public ResponseEntity<GenericResponse<List<ReviewDTO>>> getReviewsOfContent(@PathVariable ContentType contentType, @PathVariable Long contentId) {
        return ResponseEntity.ok(GenericResponse.success(reviewService.getReviewsOfContent(contentType, contentId)));
    }
}
