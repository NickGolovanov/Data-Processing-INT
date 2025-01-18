package com.example.nefix.profile;

import com.example.nefix.genrealization.service.BaseService;
import com.example.nefix.preference.Preference;
import com.example.nefix.preference.PreferenceRepository;
import com.example.nefix.watchlist.WatchList;
import com.example.nefix.watchlist.WatchListRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService extends BaseService<Profile, Long>
{
    @Autowired
    private WatchListRepository watchListRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private PreferenceRepository preferenceRepository;

    public ProfileService(ProfileRepository repository)
    {
        super(repository, List.of("profileId", "liveInfos", "watchList", "preference"));
    }

    public WatchList addToWatchList(WatchList watchList)
    {
        return watchListRepository.save(watchList);
    }

    public List<WatchList> getWatchListByProfileId(Long profileId)
    {
        return watchListRepository.findAllByProfile_ProfileId(profileId);
    }

    public List<WatchList> getWatchListProfileMovies(Long profileId)
    {
        return watchListRepository.findAllByProfile_ProfileIdAndMovieIsNotNull(profileId);
    }

    public List<WatchList> getWatchListProfileSeries(Long profileId)
    {
        return watchListRepository.findAllByProfile_ProfileIdAndSeriesIsNotNull(profileId);
    }

    @Transactional
    public void deleteMovieFromWatchList(Long profileId, Long movieId)
    {
        watchListRepository.deleteByProfile_ProfileIdAndMovie_MovieId(profileId, movieId);
    }

    @Transactional
    public void deleteSeriesFromWatchList(Long profileId, Long seriesId)
    {
        watchListRepository.deleteByProfile_ProfileIdAndSeries_SeriesId(profileId, seriesId);
    }

    public Profile updatePreferences(Long profileId, ProfilePreferencesDto requestDto)
    {
        // Fetch the profile
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Profile not found with ID: " + profileId));

        // Fetch the preference
        Preference preference = preferenceRepository.findById(requestDto.getPreferenceId())
                .orElseThrow(() -> new RuntimeException("Preference not found with ID: " + requestDto.getPreferenceId()));

        // Update the profile's preference
        profile.setPreference(preference);

        // Save and return the updated profile
        return profileRepository.save(profile);
    }

    public Preference getPreferences(Long profileId)
    {
        // Fetch the profile
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Profile not found with ID: " + profileId));

        // Retrieve and return the associated preference
        Preference preference = profile.getPreference();
        if (preference == null)
        {
            throw new RuntimeException("No preference set for profile with ID: " + profileId);
        }
        return preference;
    }
}
