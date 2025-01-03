package com.example.nefix.mediapreferences;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaPreferencesRepository extends JpaRepository<MediaPreferences, Long>
{
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM mediapreferences WHERE movie_id = :movie_id AND preference_id = :preference_id", nativeQuery = true)
    void deleteByMovieIdAndPreferenceId(Long movie_id, Long preference_id);

}
