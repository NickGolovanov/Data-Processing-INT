package com.example.nefix.preference;

import com.example.nefix.genrealization.controller.BaseController;
import com.example.nefix.genrealization.response.ApiResponse;
import com.example.nefix.genrealization.response.ErrorResponse;
import com.example.nefix.mediapreferences.MediaPreferences;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/preference")
public class PreferenceController extends BaseController<Preference, Long>
{
    @Autowired
    private PreferenceService preferenceService;

    public PreferenceController(PreferenceService service)
    {
        super(service);
    }

    @PostMapping("/{preferenceId}/movie/{movieId}")
    public ResponseEntity<ApiResponse<MediaPreferences>> addPreferenceMovie(@PathVariable Long preferenceId, @PathVariable Long movieId)
    {
        try
        {
            MediaPreferences preference = this.preferenceService.addPreferenceMovie(preferenceId, movieId);

            return ResponseEntity.ok(new ApiResponse<>(preference, null));
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, new ErrorResponse("Error creating entity: " + e.getMessage())));
        }
    }

    @PostMapping("/{preferenceId}/series/{seriesId}")
    public ResponseEntity<ApiResponse<MediaPreferences>> addPreferenceSeries(@PathVariable Long preferenceId, @PathVariable Long seriesId)
    {
        try
        {
            MediaPreferences preference = this.preferenceService.addPreferenceSeries(preferenceId, seriesId);

            return ResponseEntity.ok(new ApiResponse<>(preference, null));
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, new ErrorResponse("Error creating entity: " + e.getMessage())));
        }
    }

    @DeleteMapping("/{preferenceId}/movie/{movieId}")
    public ResponseEntity<?> deletePreferenceMovie(@PathVariable Long preferenceId, @PathVariable Long movieId)
    {
        try
        {
            ((PreferenceService) service).deletePreferenceMovie(preferenceId, movieId);
            return ResponseEntity.noContent().build();
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, new ErrorResponse("Error creating entity: " + e.getMessage())));
        }
    }

    @DeleteMapping("/{preferenceId}/series/{seriesId}")
    public ResponseEntity<?> deletePreferenceSeries(@PathVariable Long preferenceId, @PathVariable Long seriesId)
    {
        try
        {
            ((PreferenceService) service).deletePreferenceSeries(preferenceId, seriesId);
            return ResponseEntity.noContent().build();
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, new ErrorResponse("Error creating entity: " + e.getMessage())));
        }
    }

    @GetMapping("/{preferenceId}/media")
    public ResponseEntity<ApiResponse<List<MediaPreferences>>> getMediaPreferences(@PathVariable Long preferenceId)
    {
        try
        {
            List<MediaPreferences> mediaPreferences = ((PreferenceService) service).getMediaPreferences(preferenceId);

            return ResponseEntity.ok(new ApiResponse<>(mediaPreferences, null));
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, new ErrorResponse("Error creating entity: " + e.getMessage())));
        }
    }
}
