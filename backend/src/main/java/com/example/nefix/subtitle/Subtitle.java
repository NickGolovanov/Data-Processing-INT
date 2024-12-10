package com.example.nefix.subtitle;

import com.example.nefix.episode.Episode;
import com.example.nefix.movie.Movie;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Subtitle
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subtitle_id")
    private Long subtitleId;

    @ManyToOne
    @JoinColumn(name = "episode_id", insertable = false, updatable = false)
    @JsonProperty("episodeId")
    private Episode episode;

    @ManyToOne
    @JoinColumn(name = "movie_id", insertable = false, updatable = false)
    @JsonProperty("movieId")
    private Movie movie;

    @JsonProperty("language")
    private String language;

    @JsonProperty("subtitleLocation")
    @Column(name = "subtitle_location")
    private String subtitleLocation;
}
