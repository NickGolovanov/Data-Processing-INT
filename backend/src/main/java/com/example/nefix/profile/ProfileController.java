package com.example.nefix.profile;

import com.example.nefix.genrealization.controller.BaseController;
import com.example.nefix.genrealization.response.ApiResponse;
import com.example.nefix.genrealization.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.nefix.preference.Preference;
import com.example.nefix.watchlist.WatchList;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController extends BaseController<Profile, Long> {
    private ProfileService profileService;

    public ProfileController(ProfileService service) {
        super(service);
    }

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse<Profile>> create(@RequestBody Profile entity)
    {
        try
        {
            if (entity.getProfileImage() == null || entity.getProfileImage().isEmpty())
            {
                String imageUrl;

                try
                {
                    HttpClient httpClient = HttpClient.newBuilder()
                            .followRedirects(HttpClient.Redirect.NORMAL)
                            .build();

                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create("https://picsum.photos/512"))
                            .GET()
                            .build();

                    HttpResponse<Void> response = httpClient.send(request, HttpResponse.BodyHandlers.discarding());

                    List<String> locationHeaders = response.headers().allValues("location");
                    if (!locationHeaders.isEmpty())
                    {
                        imageUrl = locationHeaders.get(0);
                    } else if (response.uri() != null)
                    {
                        imageUrl = response.uri().toString();
                    } else
                    {
                        throw new RuntimeException("Unable to fetch the redirected URL");
                    }

                } catch (Exception e)
                {
                    throw new RuntimeException("Error while fetching image from Picsum Photos", e);
                }

                System.out.println("Image URL: " + imageUrl);

                entity.setProfileImage(imageUrl);
            }

            Profile savedEntity = service.save(entity);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(savedEntity, null));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, new ErrorResponse("Error creating entity: " + e.getMessage())));
        }
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
