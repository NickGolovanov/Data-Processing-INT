package com.example.nefix.series;

import com.example.nefix.infoseries.InfoSeries;
import com.example.nefix.season.Season;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Series
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seriesId;

    @OneToMany(mappedBy = "seasonId")
    private Set<Season> seasons;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "infoSeriesId")
    private Set<InfoSeries> infoSeries;

    private String title;
    private Integer views;
    private Integer minimumAge;
}
