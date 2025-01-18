package com.example.nefix.profile;

import com.example.nefix.episode.Episode;
import com.example.nefix.episode.EpisodeRepository;
import com.example.nefix.genrealization.service.BaseService;
import com.example.nefix.liveinfo.LiveInfo;
import com.example.nefix.liveinfo.LiveInfoDTO;
import com.example.nefix.liveinfo.LiveInfoRepository;
import com.example.nefix.movie.Movie;
import com.example.nefix.movie.MovieRepository;
import com.example.nefix.preference.Preference;
import com.example.nefix.preference.PreferenceRepository;
import com.example.nefix.watchlist.WatchList;
import com.example.nefix.watchlist.WatchListRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService extends BaseService<Profile, Long>
{
    @Autowired
    private WatchListRepository watchListRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private PreferenceRepository preferenceRepository;
    @Autowired
    private LiveInfoRepository liveInfoRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private EpisodeRepository episodeRepository;

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

    public Optional<LiveInfo> getLiveInfoByMovieId(Long profileId, Long movieId)
    {
        return this.liveInfoRepository.findByProfile_ProfileIdAndMovie_MovieId(profileId, movieId);
    }

    public Optional<LiveInfo> getLiveInfoByEpisodeId(Long profileId, Long episodeId)
    {
        return this.liveInfoRepository.findByProfile_ProfileIdAndEpisode_EpisodeId(profileId, episodeId);
    }

    @Transactional
    public LiveInfo createOrUpdateLiveInfoForMovie(Long profileId, Long movieId, LiveInfoDTO liveInfoDTO)
    {
        Optional<LiveInfo> liveInfo = this.liveInfoRepository.findByProfile_ProfileIdAndMovie_MovieId(profileId, movieId);

        Profile profile = this.profileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Profile not found with ID: " + profileId));

        Movie movie = this.movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found with ID: " + movieId));

        if (liveInfo.isPresent())
        {
            liveInfo.get().setProfile(profile);
            liveInfo.get().setMovie(movie);
            liveInfo.get().setWatchedTime(liveInfoDTO.getWatchedTime());

            return this.liveInfoRepository.save(liveInfo.get());
        }

        LiveInfo newLiveInfo = new LiveInfo();
        newLiveInfo.setProfile(profile);
        newLiveInfo.setMovie(movie);
        newLiveInfo.setWatchedTime(liveInfoDTO.getWatchedTime());

        return this.liveInfoRepository.save(newLiveInfo);
    }

    @Transactional
    public LiveInfo createOrUpdateLiveInfoForEpisode(Long profileId, Long episodeId, LiveInfoDTO liveInfoDTO)
    {
        Optional<LiveInfo> liveInfo = this.liveInfoRepository.findByProfile_ProfileIdAndEpisode_EpisodeId(profileId, episodeId);

        Profile profile = this.profileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Profile not found with ID: " + profileId));

        Episode episode = this.episodeRepository.findById(episodeId)
                .orElseThrow(() -> new RuntimeException("Episode not found with ID: " + episodeId));

        if (liveInfo.isPresent())
        {
            liveInfo.get().setProfile(profile);
            liveInfo.get().setEpisode(episode);
            liveInfo.get().setWatchedTime(liveInfoDTO.getWatchedTime());

            return this.liveInfoRepository.save(liveInfo.get());
        }

        LiveInfo newLiveInfo = new LiveInfo();
        newLiveInfo.setProfile(profile);
        newLiveInfo.setEpisode(episode);
        newLiveInfo.setWatchedTime(liveInfoDTO.getWatchedTime());

        return this.liveInfoRepository.save(newLiveInfo);
    }
}
