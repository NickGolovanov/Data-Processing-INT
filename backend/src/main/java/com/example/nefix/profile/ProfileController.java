package com.example.nefix.profile;

import com.example.nefix.genrealization.controller.BaseController;
import com.example.nefix.preference.Preference;
import com.example.nefix.watchlist.WatchList;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController extends BaseController<Profile, Long> {
    private ProfileService profileService;

    public ProfileController(ProfileService service) {
        super(service);
    }

    @GetMapping("/{profileId}/watch-list")
    public ResponseEntity<?> getWatchListByProfileId(@PathVariable Long profileId) {
        try {
            return ResponseEntity.ok(((ProfileService) service).getWatchListByProfileId(profileId));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/{profileId}/watch-list")
    public ResponseEntity<?> addToWatchList(@PathVariable Long profileId, @RequestBody WatchList watchList) {
        try {
            Profile profile = ((ProfileService) service).findById(profileId).orElseThrow(() -> new RuntimeException("Profile with ID " + profileId + " not found"));
            watchList.setProfile(profile);
            return ResponseEntity.ok(((ProfileService) service).addToWatchList(watchList));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{profileId}/watch-list/movies")
    public ResponseEntity<?> getWatchListProfileMovies(@PathVariable Long profileId) {
        try {
            return ResponseEntity.ok(((ProfileService) service).getWatchListProfileMovies(profileId));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/{profileId}/watch-list/movie/{movieId}")
    public ResponseEntity<?> deleteFromWatchList(@PathVariable Long profileId, @PathVariable Long movieId) {
        try {
            ((ProfileService) service).deleteMovieFromWatchList(profileId, movieId);

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/{profileId}/preferences")
    public ResponseEntity<?> createOrUpdatePreferences(@Valid @RequestBody ProfilePreferencesDto requestDto) {
        try {
            Profile updatedProfile = profileService.createOrUpdatePreferences(requestDto);
            return ResponseEntity.ok(updatedProfile);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{profileId}/preferences")
    public ResponseEntity<?> getPreferences(@PathVariable Long profileId) {
        try {
            Preference preference = profileService.getPreferences(profileId);
            return ResponseEntity.ok(preference);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
