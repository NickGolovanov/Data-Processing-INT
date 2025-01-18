package com.example.nefix.preference;

import com.example.nefix.genrealization.service.BaseService;
import com.example.nefix.mediapreferences.MediaPreferences;
import com.example.nefix.mediapreferences.MediaPreferencesRepository;
import com.example.nefix.movie.Movie;
import com.example.nefix.movie.MovieRepository;
import com.example.nefix.profile.Profile;
import com.example.nefix.profile.ProfileRepository;
import com.example.nefix.series.Series;
import com.example.nefix.series.SeriesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreferenceService extends BaseService<Preference, Long>
{
    @Autowired
    private PreferenceRepository preferenceRepository;

    @Autowired
    private SeriesRepository seriesRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MediaPreferencesRepository mediaPreferencesRepository;

    public PreferenceService(PreferenceRepository repository)
    {
        super(repository, List.of("preferenceId"));
    }

    @Transactional
    public MediaPreferences addPreferenceMovie(Long preferenceId, Long movieId)
    {
        Preference preference = this.preferenceRepository.findById(preferenceId)
                .orElseThrow(() -> new RuntimeException("Preference not found with ID: " + preferenceId));

        Movie movie = this.movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found with ID: " + movieId));

        MediaPreferences mediaPreferences = new MediaPreferences();
        mediaPreferences.setPreference(preference);
        mediaPreferences.setMovie(movie);

        this.mediaPreferencesRepository.save(mediaPreferences);

        return mediaPreferences;
    }

    @Transactional
    public MediaPreferences addPreferenceSeries(Long preferenceId, Long seriesId)
    {
        Preference preference = this.preferenceRepository.findById(preferenceId)
                .orElseThrow(() -> new RuntimeException("Preference not found with ID: " + preferenceId));

        Series series = this.seriesRepository.findById(seriesId)
                .orElseThrow(() -> new RuntimeException("Series not found with ID: " + seriesId));

        MediaPreferences mediaPreferences = new MediaPreferences();
        mediaPreferences.setPreference(preference);
        mediaPreferences.setSeries(series);

        this.mediaPreferencesRepository.save(mediaPreferences);

        return mediaPreferences;
    }

    @Transactional
    public void deletePreferenceMovie(Long preferenceId, Long movieId)
    {
        this.preferenceRepository.callDeletePreferenceMovie(preferenceId, movieId);
    }

    @Transactional
    public void deletePreferenceSeries(Long preferenceId, Long seriesId)
    {
        this.preferenceRepository.callDeletePreferenceSeries(preferenceId, seriesId);
    }

    public List<MediaPreferences> getMediaPreferences(Long preferenceId)
    {
        return this.mediaPreferencesRepository.findAllByPreference_PreferenceId(preferenceId);
    }
}
