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
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreferenceService extends BaseService<Preference, Long> {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private SeriesRepository seriesRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MediaPreferencesRepository mediaPreferencesRepository;
    @Autowired
    private PreferenceRepository preferenceRepository;

    public PreferenceService(PreferenceRepository repository) {
        super(repository, List.of("preferenceId"));
    }

    public Preference addPreferenceMovie(Long profileId, Long movieId) {
//        Preference preference = new Preference();
//        Profile profile = profileRepository.findById(profileId).orElseThrow(() -> new RuntimeException("Profile not found with ID: " + profileId));
//        preference.setProfile(profile);
//        repository.save(preference);
//
//        MediaPreferences mediaPreferences = new MediaPreferences();
//
//        Movie movie = movieRepository.findById(movieId)
//                .orElseThrow(() -> new RuntimeException("Movie not found with ID: " + movieId));
//
//        mediaPreferences.setMovie(movie);
//        mediaPreferences.setPreference(preference);
//
//        mediaPreferencesRepository.save(mediaPreferences);
        long preferenceId = ((PreferenceRepository) repository).callAddPreferenceMovie(profileId, movieId);
        return repository.findById(preferenceId).orElseThrow(() -> new RuntimeException("Preference not found"));
    }

    public Preference addPreferenceSeries(Long preferenceId, Long seriesId) {
        Preference preference = repository.findById(preferenceId)
                .orElseThrow(() -> new RuntimeException("Preference not found with ID: " + preferenceId));

        Series series = seriesRepository.findById(seriesId)
                .orElseThrow(() -> new RuntimeException("Series not found with ID: " + seriesId));

        MediaPreferences mediaPreferences = new MediaPreferences();
        mediaPreferences.setPreference(preference);
        mediaPreferences.setSeries(series);

        mediaPreferencesRepository.save(mediaPreferences);
        return preference;
    }

    @Transactional
    public void deletePreferenceMovie(Long preferenceId, Long movieId) {
//        mediaPreferencesRepository.deleteByMovieIdAndPreferenceId(movieId, preferenceId);
//        repository.deleteById(preferenceId);
        preferenceRepository.callDeletePreferenceMovie(preferenceId, movieId);
    }

    @Transactional
    public void deletePreferenceSeries(Long preferenceId, Long seriesId) {
//        mediaPreferencesRepository.deleteBySeriesIdAndPreferenceId(seriesId, preferenceId);
//        repository.deleteById(preferenceId);V
        preferenceRepository.callDeletePreferenceSeries(preferenceId, seriesId);

    }

    public List<MediaPreferences> getMediaPreferences(Long preferenceId) {
        Preference preference = repository.findById(preferenceId)
                .orElseThrow(() -> new RuntimeException("Preference not found with ID: " + preferenceId));

        return mediaPreferencesRepository.findAllByPreference(preference);
    }
}
