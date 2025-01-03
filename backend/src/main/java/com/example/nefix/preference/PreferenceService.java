package com.example.nefix.preference;

import com.example.nefix.genrealization.service.BaseService;
import com.example.nefix.mediapreferences.MediaPreferences;
import com.example.nefix.mediapreferences.MediaPreferencesRepository;
import com.example.nefix.movie.MovieRepository;
import com.example.nefix.profile.Profile;
import com.example.nefix.profile.ProfileRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PreferenceService extends BaseService<Preference, Long>
{
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MediaPreferencesRepository mediaPreferencesRepository;

    public PreferenceService(PreferenceRepository repository)
    {
        super(repository);
    }

    public Preference getPreferenceByPreferenceId(Long preferenceId)
    {
        return ((PreferenceRepository) repository).getPreferenceByPreferenceId(preferenceId);
    }

    public Preference addPreferenceMovie(Long profileId, Long movieId)
    {
        Preference preference = new Preference();
        preference.setProfile(profileRepository.getProfileByProfileId(profileId));
        repository.save(preference);

        MediaPreferences mediaPreferences = new MediaPreferences();
        mediaPreferences.setMovie(movieRepository.getMovieByMovieId(movieId));
        mediaPreferences.setPreference(preference);

        mediaPreferencesRepository.save(mediaPreferences);
        return preference;
    }

    @Transactional
    public void deletePreferenceMovie(Long preferenceId, Long movieId)
    {
        mediaPreferencesRepository.deleteByMovieIdAndPreferenceId(movieId, preferenceId);
        repository.deleteById(preferenceId);

    }
}
