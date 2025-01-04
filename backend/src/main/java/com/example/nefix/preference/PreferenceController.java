package com.example.nefix.preference;

import com.example.nefix.genrealization.controller.BaseController;
import com.example.nefix.mediapreferences.MediaPreferences;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/preference")
public class PreferenceController extends BaseController<Preference, Long>
{
    public PreferenceController(PreferenceService service)
    {
        super(service);
    }

    @PostMapping("/{preferenceId}/movie/{movieId}")
    public Preference addPreferenceMovie(@PathVariable Long preferenceId, @PathVariable Long movieId)
    {
        return ((PreferenceService) service).addPreferenceMovie(preferenceId, movieId);
    }

    @PostMapping("/{preferenceId}/series/{seriesId}")
    public Preference addPreferenceSeries(@PathVariable Long preferenceId, @PathVariable Long seriesId)
    {
        return ((PreferenceService) service).addPreferenceSeries(preferenceId, seriesId);
    }

    @DeleteMapping("/{preferenceId}/movie/{movieId}")
    public void deletePreferenceMovie(@PathVariable Long preferenceId, @PathVariable Long movieId)
    {
        ((PreferenceService) service).deletePreferenceMovie(preferenceId, movieId);
    }

    @DeleteMapping("/{preferenceId}/series/{seriesId}")
    public void deletePreferenceSeries(@PathVariable Long preferenceId, @PathVariable Long seriesId)
    {
        ((PreferenceService) service).deletePreferenceSeries(preferenceId, seriesId);
    }

    @GetMapping("/{preferenceId}/media")
    public List<MediaPreferences> getMediaPreferences(@PathVariable Long preferenceId)
    {
        return ((PreferenceService) service).getMediaPreferences(preferenceId);
    }
}
