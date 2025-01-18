package com.example.nefix.watchlist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WatchListRepository extends JpaRepository<WatchList, Long>
{

    List<WatchList> findAllByProfile_ProfileId(Long profileId);

    List<WatchList> findAllByProfile_ProfileIdAndMovieIsNotNull(Long profileId);

    List<WatchList> findAllByProfile_ProfileIdAndSeriesIsNotNull(Long profileId);

    @Modifying
    void deleteByProfile_ProfileIdAndMovie_MovieId(Long profileId, Long movieId);

    @Modifying
    void deleteByProfile_ProfileIdAndSeries_SeriesId(Long profileId, Long seriesId);

    @Query(value = "CALL add_series_to_watchlist(:profileId, :seriesId, null)", nativeQuery = true)
    Long addSeriesToWatchList(
            @Param("profileId") Long profileId,
            @Param("seriesId") Long seriesId
    );

    @Query(value = "CALL add_movie_to_watchlist(:profileId, :movieId, null)", nativeQuery = true)
    Long addMovieToWatchList(
            @Param("profileId") Long profileId,
            @Param("movieId") Long movieId
    );
}
