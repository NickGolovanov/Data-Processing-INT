package com.example.nefix.infomovie;

import com.example.nefix.info.Info;
import com.example.nefix.movie.Movie;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@IdClass(InfoMovieId.class)
public class InfoMovie
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long movieId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "info_id")
    private Long infoId;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false, insertable = false, updatable = false)
    @JsonProperty("movieId")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "info_id", nullable = false, insertable = false, updatable = false)
    @JsonProperty("infoId")
    private Info info;
}
