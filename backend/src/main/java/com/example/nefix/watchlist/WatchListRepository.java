package com.example.nefix.watchlist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WatchListRepository extends JpaRepository<WatchList, Long>
{

    List<WatchList> findAllByProfile_ProfileId(Long profileId);

    List<WatchList> findAllByProfile_ProfileIdAndMovieIsNotNull(Long profileId);

    List<WatchList> findAllByProfile_ProfileIdAndSeriesIsNotNull(Long profileId);

    @Modifying
    void deleteByProfile_ProfileIdAndMovie_MovieId(Long profileId, Long movieId);

    @Modifying
    void deleteByProfile_ProfileIdAndSeries_SeriesId(Long profileId, Long seriesId);
}
