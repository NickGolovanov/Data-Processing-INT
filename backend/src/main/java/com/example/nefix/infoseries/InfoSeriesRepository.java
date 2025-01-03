package com.example.nefix.infoseries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InfoSeriesRepository extends JpaRepository<InfoSeries, Long>
{
    List<InfoSeries> findAllBySeries_SeriesId(Long seriesId);

    Optional<InfoSeries> findBySeries_SeriesIdAndInfo_InfoId(Long seriesId, Long infoId);
}
