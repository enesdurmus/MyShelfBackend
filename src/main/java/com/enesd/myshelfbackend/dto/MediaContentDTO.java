package com.enesd.myshelfbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MediaContentDTO {
    private String imdbId;
    private String title;
    private Float voteAverage;
    private Integer voteCount;
    private Instant releaseDate;
    private Long revenue;
    private Integer runtime;
    private boolean isAdult;
    private String overview;
}
