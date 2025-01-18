package com.example.nefix.watchlist;

import com.example.nefix.genrealization.service.BaseService;
import com.example.nefix.movie.Movie;
import com.example.nefix.profile.ProfileRepository;
import com.example.nefix.series.Series;
import com.example.nefix.series.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WatchListService extends BaseService<WatchList, Long>
{
    @Autowired
    private WatchListRepository watchListRepository;

    public WatchListService(WatchListRepository repository)
    {
        super(repository, List.of("watchListId"));
    }

    public WatchList addSeriesToWatchList(Long profileId, Long seriesId)
    {
        Long watchlistId = this.watchListRepository.addSeriesToWatchList(profileId, seriesId);

        return this.watchListRepository.findById(watchlistId)
                .orElseThrow(() -> new RuntimeException("Failed to add series to watchlist"));
    }


    public List<Series> getSeriesFromWatchList(Long profileId)
    {
        return this.watchListRepository.findAllByProfile_ProfileIdAndSeriesIsNotNull(profileId)
                .stream()
                .map(WatchList::getSeries)
                .collect(Collectors.toList());
    }

    @Transactional
    public void removeSeriesFromWatchList(Long profileId, Long seriesId)
    {
        this.watchListRepository.deleteByProfile_ProfileIdAndSeries_SeriesId(profileId, seriesId);
    }

    public WatchList addMovieToWatchList(Long profileId, Long movieId)
    {
        Long watchlistId = this.watchListRepository.addMovieToWatchList(profileId, movieId);

        return this.watchListRepository.findById(watchlistId)
                .orElseThrow(() -> new RuntimeException("Failed to add series to watchlist"));
    }


    public List<Movie> getMoviesFromWatchList(Long profileId)
    {
        return this.watchListRepository.findAllByProfile_ProfileIdAndMovieIsNotNull(profileId)
                .stream()
                .map(WatchList::getMovie)
                .collect(Collectors.toList());
    }

    @Transactional
    public void removeMovieFromWatchList(Long profileId, Long movieId)
    {
        this.watchListRepository.deleteByProfile_ProfileIdAndMovie_MovieId(profileId, movieId);
    }
}
