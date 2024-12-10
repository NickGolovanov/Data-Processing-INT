package com.example.nefix.infomovie;

import com.example.nefix.accountsubscription.AccountSubscriptionId;
import com.example.nefix.info.Info;
import com.example.nefix.movie.Movie;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class InfoMovie
{
    @EmbeddedId
    @JsonProperty("id")
    private InfoMovieId id;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false, insertable = false, updatable = false)
    @JsonProperty("movieId")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "info_id", nullable = false, insertable = false, updatable = false)
    @JsonProperty("infoId")
    private Info info;
}
