package com.example.nefix.profile;

import com.example.nefix.genrealization.service.BaseService;
import com.example.nefix.watchlist.WatchList;
import com.example.nefix.watchlist.WatchListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
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

    public List<WatchList> getWatchListByProfileId(Long profileId) {
        return watchListRepository.getWatchListByProfileProfileId(profileId);
    }

    public List<WatchList> getWatchListProfileMovies(Long profileId) {
        return watchListRepository.getWatchListsProfileMovies(profileId);
    }

}
