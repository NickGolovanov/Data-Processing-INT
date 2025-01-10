package com.example.nefix.subtitle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubtitleRepository extends JpaRepository<Subtitle, Long>
{
    List<Subtitle> findAllByMovie_MovieId(Long movieId);

    List<Subtitle> findAllByEpisode_EpisodeId(Long episodeId);

    Subtitle findByMovie_MovieIdAndLanguage(Long movieMovieId, String language);

    @Query(value = "SELECT 1 FROM subtitle WHERE movie_id = :movieId and subtitle_id = :subtitleId", nativeQuery = true)
    Subtitle findBySubtitleId_MovieId(@Param("movieId") Long movieId, @Param("subtitleId") Long subtitleId);

    @Modifying
    @Query(value = "CALL update_subtitle(:subtitle_id, :movie_id, :subtitle_language, :subtitle_location)", nativeQuery = true)
    void callUpdateSubtitle(
            @Param("subtitle_id") Long subtitleId,
            @Param("movie_id") Long movieId,
            @Param("subtitle_language") String subtitleLanguage,
            @Param("subtitle_location") String subtitleLocation
    );




    @Query(value = "CALL add_subtitle(:movieId, :subtitleLanguage, :subtitleLocation, null)", nativeQuery = true)
    long callAddSubtitle(
            @Param("movieId") Long movieId,
            @Param("subtitleLanguage") String subtitleLanguage,
            @Param("subtitleLocation") String subtitleLocation);

}
