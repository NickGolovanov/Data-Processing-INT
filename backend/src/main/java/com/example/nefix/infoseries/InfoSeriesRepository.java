package com.example.nefix.infoseries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfoSeriesRepository extends JpaRepository<InfoSeries, Long>
{
    List<InfoSeries> findAllBySeries_SeriesId(Long seriesId);

    InfoSeries findBySeries_SeriesIdAndInfo_InfoId(Long seriesId, Long infoId);

    @Query(value = "CALL add_info_series(:p_series_id, :p_info_description, :p_info_type, null)", nativeQuery = true)
    Long callAddInfoSeries(
            @Param("p_series_id") Long seriesId,
            @Param("p_info_description") String infoDescription,
            @Param("p_info_type") String infoType
    );

    @Modifying
    @Query(value = "CALL delete_info_series(:p_series_id, :p_info_id)", nativeQuery = true)
    void callDeleteInfoSeries(
            @Param("p_series_id") Long seriesId,
            @Param("p_info_id") Long infoId
    );

    @Modifying
    @Query(value = "CALL update_info_series(:p_series_id, :p_movie_id, :p_info_description, :p_info_type)", nativeQuery = true)
    void callUpdateInfoSeries(
            @Param("p_info_movie_id") Long infoMovieId,
            @Param("p_series_id") Long seriesId,
            @Param("p_info_description") String infoDescription,
            @Param("p_info_type") String infoType
    );
}
