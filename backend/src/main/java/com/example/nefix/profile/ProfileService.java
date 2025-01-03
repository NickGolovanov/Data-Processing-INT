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

    public Profile getProfileByUserId(Long profileId)
    {
        return ((ProfileRepository) repository).getProfileByProfileId(profileId);
    }

    public WatchList addToWatchList(WatchList watchList) {
        return watchListRepository.save(watchList);
    }

    public List<WatchList> getWatchListByProfileId(Long profileId) {
        return watchListRepository.getWatchListByProfileProfileId(profileId);
    }

    public List<WatchList> getWatchListProfileMovies(Long profileId) {
        return watchListRepository.getWatchListsProfileMovies(profileId);
    }

    @Transactional
    public void deleteFromWatchList(Long profileId, Long movieId) {
         watchListRepository.deleteFromWatchList(profileId, movieId);
    }



}
