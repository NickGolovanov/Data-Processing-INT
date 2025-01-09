package com.example.nefix.infomovie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InfoMovieRepository extends JpaRepository<InfoMovie, Long>
{
    List<InfoMovie> findAllByMovie_MovieId(Long movieId);

    Optional<InfoMovie> findByMovie_MovieIdAndInfo_InfoId(Long movieId, Long infoId);

    @Query(value = "CALL add_info_movie(:p_movie_id, :p_info_description, :p_info_type, null)", nativeQuery = true)
    long callAddInfoMovie(
            @Param("p_movie_id") Long movieId,
            @Param("p_info_description") String infoDescription,
            @Param("p_info_type") String infoType
    );

    @Modifying
    @Query(value = "CALL delete_info_movie(:p_movie_id, :p_info_id)", nativeQuery = true)
    void callDeleteInfoMovie(
            @Param("p_movie_id") Long movieId,
            @Param("p_info_id") Long infoId
    );

    @Modifying
    @Query(value = "CALL update_info_movie(:p_info_movie_id, :p_movie_id, :p_info_description, :p_info_type)", nativeQuery = true)
    void callUpdateInfoMovie(
            @Param("p_info_movie_id") Long infoMovieId,
            @Param("p_movie_id") Long movieId,
            @Param("p_info_description") String infoDescription,
            @Param("p_info_type") String infoType
    );
}