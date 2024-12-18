package com.example.nefix.infomovie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoMovieRepository extends JpaRepository<InfoMovie, Long>
{
}
