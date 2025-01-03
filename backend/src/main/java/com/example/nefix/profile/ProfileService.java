package com.example.nefix.profile;

import com.example.nefix.genrealization.service.BaseService;
import com.example.nefix.watchlist.WatchList;
import com.example.nefix.watchlist.WatchListRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService extends BaseService<Profile, Long>
{
    @Autowired
    private WatchListRepository watchListRepository;

    public ProfileService(ProfileRepository repository)
    {
        super(repository);
    }

    public WatchList addToWatchList(WatchList watchList)
    {
        return watchListRepository.save(watchList);
    }

    public List<WatchList> getWatchListByProfileId(Long profileId)
    {
        return watchListRepository.findAllByProfile_ProfileId(profileId);
    }

    public List<WatchList> getWatchListProfileMovies(Long profileId)
    {
        return watchListRepository.findAllByProfile_ProfileIdAndMovieIsNotNull(profileId);
    }

    public List<WatchList> getWatchListProfileSeries(Long profileId)
    {
        return watchListRepository.findAllByProfile_ProfileIdAndSeriesIsNotNull(profileId);
    }

    @Transactional
    public void deleteMovieFromWatchList(Long profileId, Long movieId)
    {
        watchListRepository.deleteByProfile_ProfileIdAndMovie_MovieId(profileId, movieId);
    }

    @Transactional
    public void deleteSeriesFromWatchList(Long profileId, Long seriesId)
    {
        watchListRepository.deleteByProfile_ProfileIdAndSeries_SeriesId(profileId, seriesId);
    }

}
