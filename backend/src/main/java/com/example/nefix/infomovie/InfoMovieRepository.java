package com.example.nefix.infomovie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Modifying
    @Procedure(name = "add_info_movie")
    void callAddInfoMovie(
            @Param("p_movie_id") Long movieId,
            @Param("p_info_description") String infoDescription,
            @Param("p_info_type") String infoType
    );
}