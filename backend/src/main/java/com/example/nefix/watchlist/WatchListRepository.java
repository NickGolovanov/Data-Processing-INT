package com.example.nefix.watchlist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;
import java.util.Optional;

public interface WatchListRepository extends JpaRepository<WatchList, Long>
{

    List<WatchList> findAllByProfile_ProfileId(Long profileId);

    List<WatchList> findAllByProfile_ProfileIdAndMovieIsNotNull(Long profileId);

    List<WatchList> findAllByProfile_ProfileIdAndSeriesIsNotNull(Long profileId);

    Optional<WatchList> findByProfile_ProfileIdAndMovie_MovieId(Long profileId, Long movieId);

    Optional<WatchList> findByProfile_ProfileIdAndSeries_SeriesId(Long profileId, Long seriesId);

    @Modifying
    void deleteByProfile_ProfileIdAndMovie_MovieId(Long profileId, Long movieId);

    @Modifying
    void deleteByProfile_ProfileIdAndSeries_SeriesId(Long profileId, Long seriesId);
}
