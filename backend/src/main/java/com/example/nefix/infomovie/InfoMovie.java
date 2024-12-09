package com.example.nefix.infomovie;

import com.example.nefix.info.Info;
import com.example.nefix.movie.Movie;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@IdClass(InfoMovieId.class)
public class InfoMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long movieId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "info_id")
    private Long infoId;

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "movieId", nullable = false, insertable = false, updatable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "info_id", referencedColumnName = "infoId", nullable = false, insertable = false, updatable = false)
    private Info info;
}
