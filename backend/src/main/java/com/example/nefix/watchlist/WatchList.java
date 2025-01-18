package com.example.nefix.watchlist;

import com.example.nefix.movie.Movie;
import com.example.nefix.movie.MovieDeserializer;
import com.example.nefix.profile.Profile;
import com.example.nefix.profile.ProfileDeserializer;
import com.example.nefix.series.Series;
import com.example.nefix.series.SeriesDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"profile_id", "series_id", "movie_id"}))
public class WatchList implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "watchlist_id")
    private Long watchListId;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    @JsonProperty(value = "profileId", access = JsonProperty.Access.WRITE_ONLY)
    @JsonDeserialize(using = ProfileDeserializer.class)
    @NotNull(message = "Profile must not be null.")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "series_id")
    @JsonProperty(value = "seriesId", access = JsonProperty.Access.WRITE_ONLY)
    @JsonDeserialize(using = SeriesDeserializer.class)
    private Series series;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    @JsonProperty(value = "movieId", access = JsonProperty.Access.WRITE_ONLY)
    @JsonDeserialize(using = MovieDeserializer.class)
    private Movie movie;

    @JsonProperty(value = "profileId", access = JsonProperty.Access.READ_ONLY)
    public Long getProfileId()
    {
        return this.profile.getProfileId();
    }

    @JsonProperty(value = "movieId", access = JsonProperty.Access.READ_ONLY)
    public Long getMovieId()
    {
        if (movie == null)
        {
            return null;
        }
        return this.movie.getMovieId();
    }

    @JsonProperty(value = "seriesId", access = JsonProperty.Access.READ_ONLY)
    public Long getSeriesId()
    {
        if (series == null)
        {
            return null;
        }
        return this.series.getSeriesId();
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        WatchList that = (WatchList) o;
        return Objects.equals(this.watchListId, that.watchListId);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.watchListId);
    }
}
