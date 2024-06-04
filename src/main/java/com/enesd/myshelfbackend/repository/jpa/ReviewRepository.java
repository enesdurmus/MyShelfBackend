package com.enesd.myshelfbackend.repository.jpa;

import com.enesd.myshelfbackend.enums.ContentType;
import com.enesd.myshelfbackend.model.entities.BookEntity;
import com.enesd.myshelfbackend.model.entities.MediaContentEntity;
import com.enesd.myshelfbackend.model.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByContentTypeAndBook(ContentType contentType, BookEntity book);

    List<Review> findAllByContentTypeAndMediaContent(ContentType contentType, MediaContentEntity mediaContent);
}
