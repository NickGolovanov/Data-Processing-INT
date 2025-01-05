package com.example.nefix.watchlist;

import com.example.nefix.genrealization.service.BaseService;
import com.example.nefix.movie.Movie;
import com.example.nefix.profile.Profile;
import com.example.nefix.profile.ProfileRepository;
import com.example.nefix.series.Series;
import com.example.nefix.series.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WatchListService extends BaseService<WatchList, Long> {

    @Autowired
    private SeriesRepository seriesRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private WatchListRepository watchListRepository;

    public WatchListService(WatchListRepository repository) {
        super(repository);
    }

    /**
     * Adds a series to a profile's watchlist.
     */
    public WatchList addSeriesToWatchList(Long profileId, Long seriesId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Profile not found with ID: " + profileId));
        Series series = seriesRepository.findById(seriesId)
                .orElseThrow(() -> new RuntimeException("Series not found with ID: " + seriesId));

        WatchList watchList = new WatchList();
        watchList.setProfile(profile);
        watchList.setSeries(series);

        return watchListRepository.save(watchList);
    }

    public List<Series> getSeriesFromWatchList(Long profileId) {
        return watchListRepository.findAllByProfile_ProfileIdAndSeriesIsNotNull(profileId)
                .stream()
                .map(WatchList::getSeries)
                .collect(Collectors.toList());
    }

    /**
     * Removes a series from a profile's watchlist.
     */
    @Transactional
    public void removeSeriesFromWatchList(Long profileId, Long seriesId) {
        watchListRepository.deleteByProfile_ProfileIdAndSeries_SeriesId(profileId, seriesId);
    }
}
