package com.example.nefix.profile;

import com.example.nefix.genrealization.controller.BaseController;
import com.example.nefix.preference.Preference;
import com.example.nefix.watchlist.WatchList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController extends BaseController<Profile, Long>
{
    private ProfileService profileService;
    public ProfileController(ProfileService service)
    {
        super(service);
    }

    @GetMapping("/{profileId}/watch-list")
    public ResponseEntity<List<WatchList>> getWatchListByProfileId(@PathVariable Long profileId)
    {
        return ResponseEntity.ok(((ProfileService) service).getWatchListByProfileId(profileId));
    }

    @PostMapping("/{profileId}/watch-list")
    public ResponseEntity<WatchList> addToWatchList(@PathVariable Long profileId, @RequestBody WatchList watchList)
    {
        Profile profile = ((ProfileService) service).findById(profileId).orElseThrow(() -> new RuntimeException("Profile with ID " + profileId + " not found"));
        watchList.setProfile(profile);
        return ResponseEntity.ok(((ProfileService) service).addToWatchList(watchList));
    }

    @GetMapping("/{profileId}/watch-list/movies")
    public ResponseEntity<List<WatchList>> getWatchListProfileMovies(@PathVariable Long profileId)
    {
        return ResponseEntity.ok(((ProfileService) service).getWatchListProfileMovies(profileId));
    }

    @DeleteMapping("/{profileId}/watch-list/movie/{movieId}")
    public ResponseEntity<Void> deleteFromWatchList(@PathVariable Long profileId, @PathVariable Long movieId)
    {
        ((ProfileService) service).deleteMovieFromWatchList(profileId, movieId);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{profileId}/preferences")
    public ResponseEntity<Profile> createOrUpdatePreferences(@RequestBody ProfilePreferencesDto requestDto) {
        Profile updatedProfile = profileService.createOrUpdatePreferences(requestDto);
        return ResponseEntity.ok(updatedProfile);
    }

    @GetMapping("/{profileId}/preferences")
    public ResponseEntity<Preference> getPreferences(@PathVariable Long profileId) {
        Preference preference = profileService.getPreferences(profileId);
        return ResponseEntity.ok(preference);
    }
}
