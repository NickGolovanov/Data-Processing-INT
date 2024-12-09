package com.example.nefix.movie;

import com.example.nefix.infomovie.InfoMovie;
import com.example.nefix.subtitle.Subtitle;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long movieId;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = false)
    private Set<Subtitle> subtitles;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = false)
    private Set<InfoMovie> infoMovies;

    @JsonProperty("title")
    private String title;

    @JsonProperty("duration")
    private Integer duration;

    @JsonProperty("views")
    private Integer views;

    @JsonProperty("SD")
    private Boolean SD;

    @JsonProperty("HD")
    private Boolean HD;

    @JsonProperty("UHD")
    private Boolean UHD;
}
