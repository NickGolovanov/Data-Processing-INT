package com.example.nefix.watchlist;

import com.example.nefix.movie.Movie;
import com.example.nefix.profile.Profile;
import com.example.nefix.series.Series;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class WatchList
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "watchlist_id")
    private Long watchListId;

    @ManyToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "profileId", nullable = false, insertable = false, updatable = false)
    @JsonProperty("profileId")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "series_id", referencedColumnName = "seriesId")
    @JsonProperty("seriesId")
    private Series series;

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "movieId")
    @JsonProperty("movieId")
    private Movie movie;
}
