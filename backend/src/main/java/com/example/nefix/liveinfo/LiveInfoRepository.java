package com.example.nefix.liveinfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LiveInfoRepository extends JpaRepository<LiveInfo, Long>
{
    Optional<LiveInfo> findByProfile_ProfileIdAndMovie_MovieId(Long profileId, Long movieId);

    Optional<LiveInfo> findByProfile_ProfileIdAndEpisode_EpisodeId(Long profileId, Long episodeId);
}
