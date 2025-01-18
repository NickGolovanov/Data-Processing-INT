package com.example.nefix.infomovie;

import com.example.nefix.info.Info;
import com.example.nefix.movie.Movie;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class InfoMovie implements Serializable
{
    @EmbeddedId
    @JsonIgnore
    private InfoMovieId id;

    @ManyToOne
    @JoinColumn(name = "movie_id", insertable = false, updatable = false)
    @JsonIgnore
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "info_id", insertable = false, updatable = false)
    @JsonIgnore
    private Info info;

    @JsonProperty(value = "infoId", access = JsonProperty.Access.READ_ONLY)
    public Long getInfoId()
    {
        return this.info.getInfoId();
    }

    @JsonProperty(value = "movieId", access = JsonProperty.Access.READ_ONLY)
    public Long getMovieId()
    {
        return this.movie.getMovieId();
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        InfoMovie that = (InfoMovie) o;
        return Objects.equals(this.id, that.id);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.id);
    }

}
