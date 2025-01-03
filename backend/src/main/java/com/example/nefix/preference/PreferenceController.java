package com.example.nefix.preference;

import com.example.nefix.genrealization.controller.BaseController;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/{preferenceId}/movie/{movieId}")
    public void deletePreferenceMovie(@PathVariable Long preferenceId, @PathVariable Long movieId)
    {
        ((PreferenceService) service).deletePreferenceMovie(preferenceId, movieId);
    }
}
