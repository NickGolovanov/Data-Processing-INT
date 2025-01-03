package com.example.nefix.movie;

import com.example.nefix.subtitle.SubtitleViewDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query(value = "SELECT subtitle_id, language, subtitle_location, episode_id, episode_title, movie_id, movie_title " +
            "FROM view_subtitles WHERE movie_id IS NOT NULL", nativeQuery = true)
    List<Object[]> findMovieWithSubtitles();


    Movie getMovieByMovieId(Long movieId);
}
