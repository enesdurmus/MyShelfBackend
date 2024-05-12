package com.enesd.myshelfbackend.model.abstracts;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Data
@MappedSuperclass
public abstract class MediaContent {

    @Id
    @Column(name = "media_content_id")
    private Integer mediaContentId;

    @Column(name = "title")
    private String title;

    @Column(name = "vote_average")
    private Float voteAverage;

    @Column(name = "vote_count")
    private Integer voteCount;

    @Column(name = "release_date")
    private Instant releaseDate;

    @Column(name = "revenue")
    private Integer revenue;

    @Column(name = "runtime")
    private Integer runtime;

    @Column(name = "is_adult")
    private boolean isAdult;

    @Column(name = "image_path")
    private String imagePath;
}
