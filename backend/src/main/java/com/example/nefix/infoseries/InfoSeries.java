package com.example.nefix.infoseries;

import com.example.nefix.info.Info;
import com.example.nefix.series.Series;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@IdClass(InfoSeriesId.class)
public class InfoSeries
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "info_id")
    private Long infoId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "series_id")
    private Long seriesId;

    @ManyToOne
    @JoinColumn(name = "info_id", referencedColumnName = "infoId", insertable = false, updatable = false)
    @JsonProperty("infoId")
    private Info info;

    @ManyToOne
    @JoinColumn(name = "series_id", referencedColumnName = "seriesId", insertable = false, updatable = false)
    @JsonProperty("seriesId")
    private Series series;
}