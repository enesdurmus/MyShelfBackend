package com.enesd.myshelfbackend.services;

import com.enesd.myshelfbackend.dto.ReviewDTO;
import com.enesd.myshelfbackend.enums.ContentType;
import com.enesd.myshelfbackend.model.entities.Review;
import com.enesd.myshelfbackend.model.entities.User;
import com.enesd.myshelfbackend.model.request.CreateReviewRequest;
import com.enesd.myshelfbackend.repository.jpa.BookEntityRepository;
import com.enesd.myshelfbackend.repository.jpa.MediaContentEntityRepository;
import com.enesd.myshelfbackend.repository.jpa.ReviewRepository;
import com.enesd.myshelfbackend.utils.CustomModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookEntityRepository bookEntityRepository;
    private final MediaContentEntityRepository mediaContentEntityRepository;
    private final CustomModelMapper modelMapper;

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

    public List<ReviewDTO> getReviewsOfContent(ContentType contentType, Long contentId) {
        List<Review> reviews;

        //TODO REFACTOR HERE --- CONSIDER STRATEGY PATTERN
        if (contentType == ContentType.BOOK) {
            reviews = reviewRepository.findAllByContentTypeAndBook(contentType, bookEntityRepository.getReferenceById(contentId));
        } else if (contentType == ContentType.MEDIA_CONTENT) {
            reviews = reviewRepository.findAllByContentTypeAndMediaContent(contentType, mediaContentEntityRepository.getReferenceById(contentId));
        } else {
            throw new RuntimeException("Content Type Not Found");
        }
        return modelMapper.mapAll(reviews, ReviewDTO.class);
    }
}