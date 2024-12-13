package com.example.nefix.subtitle;

import com.example.nefix.episode.Episode;
import com.example.nefix.episode.EpisodeDeserializer;
import com.example.nefix.movie.Movie;
import com.example.nefix.movie.MovieDeserializer;
import com.example.nefix.series.SeriesDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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
    @JoinColumn(name = "episode_id")
    @JsonDeserialize(using = EpisodeDeserializer.class)
    @JsonProperty("episodeId")
    @JsonIgnoreProperties({"subtitles"})
    private Episode episode;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    @JsonProperty("movieId")
    @JsonDeserialize(using = MovieDeserializer.class)
    @JsonIgnoreProperties({"subtitles", "infoMovies"})
    private Movie movie;

    @JsonProperty("language")
    private String language;

    @JsonProperty("subtitleLocation")
    @Column(name = "subtitle_location")
    private String subtitleLocation;
}
