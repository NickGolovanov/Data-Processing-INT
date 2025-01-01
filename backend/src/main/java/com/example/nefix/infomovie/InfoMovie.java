package com.example.nefix.infomovie;

import com.example.nefix.accountsubscription.AccountSubscriptionId;
import com.example.nefix.info.Info;
import com.example.nefix.movie.Movie;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class InfoMovie {
    @EmbeddedId
    private InfoMovieId id;

    @ManyToOne
    @JoinColumn(name = "movie_id", insertable = false, updatable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "info_id", insertable = false, updatable = false)
    private Info info;

}
