package com.example.nefix.watchlist;

import com.example.nefix.movie.Movie;
import com.example.nefix.profile.Profile;
import com.example.nefix.series.Series;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class WatchList
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long watchListId;

    @ManyToOne
    @JoinColumn(name = "seriesId", insertable = false, updatable = false)
    private Series series;

    @ManyToOne
    @JoinColumn(name = "profileId", insertable = false, updatable = false)
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "movieId", insertable = false, updatable = false)
    private Movie movie;
}
