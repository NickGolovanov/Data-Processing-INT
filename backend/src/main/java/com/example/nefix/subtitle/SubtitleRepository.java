package com.example.nefix.subtitle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubtitleRepository extends JpaRepository<Subtitle, Long>
{
    @Modifying
    @Query(value = "CALL update_subtitle(:subtitle_id, :movie_id, :subtitle_language, :subtitle_location)", nativeQuery = true)
    void callUpdateSubtitle(
            @Param("subtitle_id") Long subtitleId,
            @Param("movie_id") Long movieId,
            @Param("subtitle_language") String subtitleLanguage,
            @Param("subtitle_location") String subtitleLocation
    );}
