package com.example.nefix.season;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Long>
{
    @Query(value = "CALL add_season_to_series(:seriesId, :seasonId)", nativeQuery = true)
    long addSeasonToSeries(
            @Param("seriesId") Long seriesId,
            @Param("seasonId") Long seasonId
    );
}
