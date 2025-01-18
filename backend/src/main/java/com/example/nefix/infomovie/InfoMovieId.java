package com.example.nefix.infomovie;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class InfoMovieId implements Serializable
{
    @Column(name = "movie_id")
    private Long movieId;

    @Column(name = "info_id")
    private Long infoId;

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        InfoMovieId that = (InfoMovieId) o;
        return Objects.equals(infoId, that.infoId) && Objects.equals(movieId, that.movieId);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(infoId, movieId);
    }
}

