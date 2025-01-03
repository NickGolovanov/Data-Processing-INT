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

    @Modifying
    @Procedure(name = "update_subtitle")
    void callUpdateSubtitle(
            @Param("subtitle_id") Long subtitleId,
            @Param("movie_id") Long movieId,
            @Param("subtitle_language") String subtitleLanguage,
            @Param("subtitle_location") String subtitleLocation
    );
}
