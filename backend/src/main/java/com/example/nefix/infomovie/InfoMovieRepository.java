package com.example.nefix.infomovie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfoMovieRepository extends JpaRepository<InfoMovie, Long>
{
    @Query(value = "SELECT * FROM infomovie WHERE movie_id = :id", nativeQuery = true)
    List<InfoMovie> getInfoMoviesByMovieId(Long id);

    @Query(value = "SELECT * FROM infomovie WHERE info_id = :info_id AND movie_id = :movie_id", nativeQuery = true)
    InfoMovie getInfoMovieByInfoIdAndMovieId(Long info_id, Long movie_id);
}