package com.example.nefix.preference;

import com.example.nefix.genrealization.controller.BaseController;
import com.example.nefix.mediapreferences.MediaPreferences;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/preference")
public class PreferenceController extends BaseController<Preference, Long>
{
    public PreferenceController(PreferenceService service)
    {
        super(service);
    }

    @PostMapping("/{preferenceId}/movie/{movieId}")
    public ResponseEntity<?> addPreferenceMovie(@PathVariable Long preferenceId, @PathVariable Long movieId)
    {
        try {
            Preference preference = ((PreferenceService) service).addPreferenceMovie(preferenceId, movieId);
            return ResponseEntity.ok(preference);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/{preferenceId}/series/{seriesId}")
    public ResponseEntity<?> addPreferenceSeries(@PathVariable Long preferenceId, @PathVariable Long seriesId)
    {
        try {
            Preference preference = ((PreferenceService) service).addPreferenceSeries(preferenceId, seriesId);
            return ResponseEntity.ok(preference);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/{preferenceId}/movie/{movieId}")
    public ResponseEntity<?> deletePreferenceMovie(@PathVariable Long preferenceId, @PathVariable Long movieId)
    {
        try {
            ((PreferenceService) service).deletePreferenceMovie(preferenceId, movieId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/{preferenceId}/series/{seriesId}")
    public ResponseEntity<?> deletePreferenceSeries(@PathVariable Long preferenceId, @PathVariable Long seriesId)
    {
        try {
            ((PreferenceService) service).deletePreferenceSeries(preferenceId, seriesId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{preferenceId}/media")
    public ResponseEntity<?> getMediaPreferences(@PathVariable Long preferenceId)
    {
        try {
            List<MediaPreferences> mediaPreferences = ((PreferenceService) service).getMediaPreferences(preferenceId);
            return ResponseEntity.ok(mediaPreferences);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
