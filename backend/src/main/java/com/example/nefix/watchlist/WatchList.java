package com.example.nefix.watchlist;

import com.example.nefix.movie.Movie;
import com.example.nefix.movie.MovieDeserializer;
import com.example.nefix.profile.Profile;
import com.example.nefix.profile.ProfileDeserializer;
import com.example.nefix.series.Series;
import com.example.nefix.series.SeriesDeserializer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"profile_id", "series_id", "movie_id"}))
public class WatchList
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "watchlist_id")
    private Long watchListId;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    @JsonProperty("profileId")
    @JsonDeserialize(using = ProfileDeserializer.class)
    @JsonIgnoreProperties({"watchList", "liveInfos", "preference"})
    @NotNull(message = "Profile must not be null.")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "series_id")
    @JsonProperty("seriesId")
    @JsonDeserialize(using = SeriesDeserializer.class)
    @JsonIgnoreProperties({"season", "infoSeries"})
    private Series series;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    @JsonProperty("movieId")
    @JsonDeserialize(using = MovieDeserializer.class)
    @JsonIgnoreProperties({"subtitles", "infoMovies"})
    private Movie movie;
}
