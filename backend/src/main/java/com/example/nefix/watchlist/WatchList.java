package com.example.nefix.watchlist;

import com.example.nefix.movie.Movie;
import com.example.nefix.profile.Profile;
import com.example.nefix.series.Series;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class WatchList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long watchListId;

    @ManyToOne
    // name is the name of column in db, referencedColumnName is the name of column in hibernate
    @JoinColumn(name = "profile_id", referencedColumnName = "profileId", nullable = false, insertable = false, updatable = false)
    private Profile profile;

    @ManyToOne
    // name is the name of column in db, referencedColumnName is the name of column in hibernate
    @JoinColumn(name = "series_id", referencedColumnName = "seriesId")
    private Series series;

    @ManyToOne
    // name is the name of column in db, referencedColumnName is the name of column in hibernate
    @JoinColumn(name = "movie_id", referencedColumnName = "movieId")
    private Movie movie;
}
