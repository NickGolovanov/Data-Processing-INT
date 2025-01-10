package com.example.nefix.preference;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferenceRepository extends JpaRepository<Preference, Long>
{
    @Query(value = "CALL add_preference_movie(:p_profile_id, :p_movie_id, null)", nativeQuery = true)
    long callAddPreferenceMovie(@Param("p_profile_id") Long p_profile_id, @Param("p_movie_id") Long p_movie_id);

    @Modifying
    @Query(value = "CALL delete_preference_movie(:p_preference_id, :p_movie_id)", nativeQuery = true)
    void callDeletePreferenceMovie(@Param("p_preference_id") Long p_preference_id, @Param("p_movie_id") Long p_movie_id);

    @Modifying
    @Query(value = "CALL delete_preference_series(:p_preference_id, :p_series_id)", nativeQuery = true)
    void callDeletePreferenceSeries(@Param("p_preference_id") Long p_preference_id, @Param("p_series_id") Long p_series_id);

}
