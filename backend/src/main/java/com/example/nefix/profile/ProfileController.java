package com.example.nefix.profile;

import com.example.nefix.genrealization.controller.BaseController;
import com.example.nefix.watchlist.WatchList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController extends BaseController<Profile, Long>
{
    public ProfileController(ProfileService service)
    {
        super(service);
    }

    @GetMapping("/{profileId}/watch-list")
    public List<WatchList> getWatchListByProfileId(@PathVariable Long profileId)
    {
        return ((ProfileService) service).getWatchListByProfileId(profileId);
    }

    @GetMapping("/{profileId}/watch-list/movies")
    public List<WatchList> getWatchListProfileMovies(@PathVariable Long profileId)
    {
        return ((ProfileService) service).getWatchListProfileMovies(profileId);
    }
}
