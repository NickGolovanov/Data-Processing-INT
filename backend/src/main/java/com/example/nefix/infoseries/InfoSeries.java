package com.example.nefix.infoseries;

import com.example.nefix.info.Info;
import com.example.nefix.series.Series;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@IdClass(InfoSeriesId.class)
public class InfoSeries
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long infoId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seriesId;

    @ManyToOne
    @JoinColumn(name = "infoId", insertable = false, updatable = false)
    private Info info;

    @ManyToOne
    @JoinColumn(name = "seriesId", insertable = false, updatable = false)
    private Series series;
}