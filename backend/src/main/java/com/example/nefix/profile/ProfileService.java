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

    public List<WatchList> getWatchListByProfileId(Long profileId)
    {
        return this.watchListRepository.findAllByProfile_ProfileId(profileId);
    }

    public Profile updatePreferences(Long profileId, ProfilePreferencesDto requestDto)
    {
        Profile profile = this.profileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Profile not found with ID: " + profileId));

        Preference preference = this.preferenceRepository.findById(requestDto.getPreferenceId())
                .orElseThrow(() -> new RuntimeException("Preference not found with ID: " + requestDto.getPreferenceId()));

        profile.setPreference(preference);

        return profileRepository.save(profile);
    }

    public Preference getPreferences(Long profileId)
    {
        Profile profile = this.profileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Profile not found with ID: " + profileId));

        Preference preference = profile.getPreference();

        if (preference == null)
        {
            throw new RuntimeException("No preference set for profile with ID: " + profileId);
        }

        return preference;
    }
}
