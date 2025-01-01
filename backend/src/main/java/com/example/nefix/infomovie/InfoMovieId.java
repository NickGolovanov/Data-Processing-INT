package com.example.nefix.infomovie;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class InfoMovieId implements Serializable {
    @Column(name = "movie_id")
    private Long movieId;

    @Column(name = "info_id")
    private Long infoId;

    public InfoMovieId() {
    }

    public InfoMovieId(Long infoId, Long movieId) {
        this.infoId = infoId;
        this.movieId = movieId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfoMovieId that = (InfoMovieId) o;
        return Objects.equals(infoId, that.infoId) && Objects.equals(movieId, that.movieId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(infoId, movieId);
    }
}

