package com.example.nefix.infomovie;

import com.example.nefix.accountsubscription.AccountSubscriptionId;
import com.example.nefix.blockedaccount.BlockedAccount;
import com.example.nefix.info.Info;
import com.example.nefix.movie.Movie;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
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
    private InfoMovieId id;

    @ManyToOne
    @JoinColumn(name = "movie_id", insertable = false, updatable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "info_id", insertable = false, updatable = false)
    private Info info;

    @JsonProperty("infoId")
    public Long getInfoId()
    {
        return this.info.getInfoId();
    }

    @JsonProperty("movieId")
    public Long getMovieId()
    {
        return this.movie.getMovieId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfoMovie that = (InfoMovie) o;
        return Objects.equals(this.id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

}
