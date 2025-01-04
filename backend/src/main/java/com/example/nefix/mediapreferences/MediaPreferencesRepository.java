package com.example.nefix.mediapreferences;

import com.example.nefix.preference.Preference;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaPreferencesRepository extends JpaRepository<MediaPreferences, Long>
{
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM mediapreferences WHERE movie_id = :movie_id AND preference_id = :preference_id", nativeQuery = true)
    void deleteByMovieIdAndPreferenceId(Long movie_id, Long preference_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM mediapreferences WHERE series_id = :series_id AND preference_id = :preference_id", nativeQuery = true)
    void deleteBySeriesIdAndPreferenceId(@Param("series_id") Long seriesId, @Param("preference_id") Long preferenceId);

    @Query(value = "SELECT m FROM MediaPreferences m WHERE m.preference = :preference")
    List<MediaPreferences> findAllByPreference(@Param("preference") Preference preference);
}
