package com.example.nefix.infomovie;

import java.io.Serializable;
import java.util.Objects;

public class InfoMovieId implements Serializable {
    private Long movieId;

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

