package com.example.nefix.infomovie;

import com.example.nefix.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InfoMovieRepository extends JpaRepository<InfoMovie, Long>
{
    List<InfoMovie> findAllByMovie_MovieId(Long movieId);

    Optional<InfoMovie> findByMovie_MovieIdAndInfo_InfoId(Long movieId, Long infoId);
}