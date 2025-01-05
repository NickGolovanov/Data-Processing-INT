package com.example.nefix.watchlist;

import com.example.nefix.genrealization.service.BaseService;
import com.example.nefix.series.Series;
import com.example.nefix.series.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WatchListService extends BaseService<WatchList, Long>
{
    //    @Autowired
    //    private SeriesRepository seriesRepository;

    public WatchListService(WatchListRepository repository)
    {
        super(repository);
    }

    //    public WatchList addSeriesToWatchList(Long watchlistId, Long seriesId) {
    //        WatchList watchList = repository.findById(watchlistId)
    //                .orElseThrow(() -> new RuntimeException("WatchList not found with ID: " + watchlistId));
    //        Series series = seriesRepository.findById(seriesId)
    //                .orElseThrow(() -> new RuntimeException("Series not found with ID: " + seriesId));
    //
    //        watchList.getSeries().add(series);
    //        return repository.save(watchList);
    //    }
    //
    //    public WatchList getSeriesFromWatchList(Long watchlistId, Long seriesId) {
    //        WatchList watchList = repository.findById(watchlistId)
    //                .orElseThrow(() -> new RuntimeException("WatchList not found with ID: " + watchlistId));
    //        return watchList.getSeries().stream()
    //                .filter(series -> series.getSeriesId().equals(seriesId))
    //                .findFirst()
    //                .map(series -> watchList)
    //                .orElseThrow(() -> new RuntimeException("Series not found in WatchList"));
    //    }
    //
    //    public void removeSeriesFromWatchList(Long watchlistId, Long seriesId) {
    //        WatchList watchList = repository.findById(watchlistId)
    //                .orElseThrow(() -> new RuntimeException("WatchList not found with ID: " + watchlistId));
    //        Series series = seriesRepository.findById(seriesId)
    //                .orElseThrow(() -> new RuntimeException("Series not found with ID: " + seriesId));
    //
    //        watchList.getSeries().remove(series);
    //        repository.save(watchList);
    //    }
}
