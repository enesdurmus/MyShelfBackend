package com.enesd.myshelfbackend.services;

import com.enesd.myshelfbackend.enums.ContentType;
import com.enesd.myshelfbackend.model.entities.Review;
import com.enesd.myshelfbackend.model.entities.User;
import com.enesd.myshelfbackend.model.request.CreateReviewRequest;
import com.enesd.myshelfbackend.repository.jpa.BookEntityRepository;
import com.enesd.myshelfbackend.repository.jpa.MediaContentEntityRepository;
import com.enesd.myshelfbackend.repository.jpa.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookEntityRepository bookEntityRepository;
    private final MediaContentEntityRepository mediaContentEntityRepository;

    public void createReview(User user, CreateReviewRequest createReviewRequest) {
        Review review = new Review();
        review.setUser(user);
        review.setReview(createReviewRequest.getReview());
        review.setRating(createReviewRequest.getRating());
        review.setContentType(createReviewRequest.getContentType());
        if (createReviewRequest.getContentType() == ContentType.BOOK) {
            review.setBook(bookEntityRepository.getReferenceById(createReviewRequest.getContentId()));
        } else if (createReviewRequest.getContentType() == ContentType.MEDIA_CONTENT) {
            review.setMediaContent(mediaContentEntityRepository.getReferenceById(createReviewRequest.getContentId()));
        } else {
            throw new RuntimeException("Content Type Not Found");
        }
        reviewRepository.save(review);
    }
}