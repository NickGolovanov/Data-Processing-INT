package com.example.nefix.profile;

import com.example.nefix.genrealization.controller.BaseController;
import com.example.nefix.genrealization.response.ApiResponse;
import com.example.nefix.genrealization.response.ErrorResponse;
import com.example.nefix.preference.Preference;
import com.example.nefix.watchlist.WatchList;
import com.example.nefix.watchlist.WatchListService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController extends BaseController<Profile, Long>
{
    @Autowired
    private ProfileService profileService;

    public ProfileController(ProfileService service)
    {
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
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, new ErrorResponse("Error creating entity: " + e.getMessage())));
        }
    }

    @GetMapping("/{profileId}/watch-list")
    public ResponseEntity<ApiResponse<List<WatchList>>> getWatchListByProfileId(@PathVariable Long profileId)
    {
        try
        {
            List<WatchList> watchLists = this.profileService.getWatchListByProfileId(profileId);

            return ResponseEntity.ok(new ApiResponse<>(watchLists, null));
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, new ErrorResponse("Error creating entity: " + e.getMessage())));
        }
    }

    @PatchMapping("/{profileId}/preferences")
    public ResponseEntity<ApiResponse<Profile>> updatePreferences(@PathVariable Long profileId, @Valid @RequestBody ProfilePreferencesDto requestDto)
    {
        try
        {
            Profile updatedProfile = this.profileService.updatePreferences(profileId, requestDto);

            return ResponseEntity.ok(new ApiResponse<>(updatedProfile, null));
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, new ErrorResponse("Error creating entity: " + e.getMessage())));
        }
    }

    @GetMapping("/{profileId}/preference")
    public ResponseEntity<ApiResponse<Preference>> getPreferences(@PathVariable Long profileId)
    {
        try
        {
            Preference preference = profileService.getPreferences(profileId);

            return ResponseEntity.ok(new ApiResponse<>(preference, null));
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, new ErrorResponse("Error creating entity: " + e.getMessage())));
        }
    }
}
