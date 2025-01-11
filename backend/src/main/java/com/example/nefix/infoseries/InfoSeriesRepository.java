package com.example.nefix.infoseries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InfoSeriesRepository extends JpaRepository<InfoSeries, Long>
{
    List<InfoSeries> findAllBySeries_SeriesId(Long seriesId);

    Optional<InfoSeries> findBySeries_SeriesIdAndInfo_InfoId(Long seriesId, Long infoId);

    @Query(value = "CALL add_info_to_series(:seriesId, :infoId)", nativeQuery = true)
    void addInfoToSeries(
            @Param("seriesId") Long seriesId,
            @Param("infoId") Long infoId
    );
}
