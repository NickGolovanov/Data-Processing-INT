package com.example.nefix.movie;

import com.example.nefix.infomovie.InfoMovie;
import com.example.nefix.subtitle.Subtitle;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = false)
    private Set<Subtitle> subtitles;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = false)
    private Set<InfoMovie> infoMovies;

    private String title;
    private Integer duration;
    private Integer views;
    private Boolean SD;
    private Boolean HD;
    private Boolean UHD;
}
