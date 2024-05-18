package com.enesd.myshelfbackend.model.abstracts;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Data
@MappedSuperclass
public abstract class MediaContent {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "imdb_id", unique = true)
    private String imdbId;

    @Column(name = "title")
    private String title;

    @Column(name = "vote_average")
    private Float voteAverage;

    @Column(name = "vote_count")
    private Integer voteCount;

    @Column(name = "release_date")
    private Instant releaseDate;

    @Column(name = "revenue")
    private Long revenue;

    @Column(name = "runtime")
    private Integer runtime;

    @Column(name = "is_adult")
    private boolean isAdult;

    @Column(name = "overview", columnDefinition = "TEXT")
    private String overview;

    @Column(name = "backdrop_path")
    private String backdropPath;

    @Column(name = "poster_path")
    private String posterPath;

    @Column(name = "original_language")
    private String originalLanguage;

    @Column(name = "tagline")
    private String tagline;

    @Column(name = "production_companies", columnDefinition = "TEXT")
    private String productionCompanies;

    @Column(name = "keywords", columnDefinition = "TEXT")
    private String keywords;
}
