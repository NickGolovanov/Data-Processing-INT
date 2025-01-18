package com.example.nefix.subtitle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubtitleRepository extends JpaRepository<Subtitle, Long>
{
    List<Subtitle> findAllByMovie_MovieId(Long movieId);

    List<Subtitle> findAllByEpisode_EpisodeId(Long episodeId);

    Subtitle findSubtitleBySubtitleIdAndMovie_MovieId(Long subtitleId, Long movieId);

    Subtitle findSubtitleBySubtitleIdAndEpisode_EpisodeId(Long subtitleId, Long episodeId);

    @Query(value = "CALL add_subtitle_movie(:movieId, :subtitleLanguage, :subtitleLocation, null)", nativeQuery = true)
    Long callAddSubtitleMovie(
            @Param("movieId") Long movieId,
            @Param("subtitleLanguage") String subtitleLanguage,
            @Param("subtitleLocation") String subtitleLocation);

    @Modifying
    @Query(value = "CALL update_subtitle_movie(:subtitle_id, :movie_id, :subtitle_language, :subtitle_location)", nativeQuery = true)
    void callUpdateSubtitleMovie(
            @Param("subtitle_id") Long subtitleId,
            @Param("movie_id") Long movieId,
            @Param("subtitle_language") String subtitleLanguage,
            @Param("subtitle_location") String subtitleLocation
    );

    @Query(value = "CALL add_subtitle_episode(:episode_id, :subtitleLanguage, :subtitleLocation, null)", nativeQuery = true)
    Long callAddSubtitleEpisode(
            @Param("episode_id") Long episode_id,
            @Param("subtitleLanguage") String subtitleLanguage,
            @Param("subtitleLocation") String subtitleLocation);

    @Modifying
    @Query(value = "CALL update_subtitle_episode(:subtitle_id, :episode_id, :subtitle_language, :subtitle_location)", nativeQuery = true)
    void callUpdateSubtitleEpisode(
            @Param("subtitle_id") Long subtitleId,
            @Param("episode_id") Long episodeId,
            @Param("subtitle_language") String subtitleLanguage,
            @Param("subtitle_location") String subtitleLocation
    );
}
