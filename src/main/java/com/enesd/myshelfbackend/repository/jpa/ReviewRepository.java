package com.enesd.myshelfbackend.repository.jpa;

import com.enesd.myshelfbackend.model.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
