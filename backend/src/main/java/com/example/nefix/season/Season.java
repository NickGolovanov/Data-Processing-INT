package com.example.nefix.season;

import com.example.nefix.episode.Episode;
import com.example.nefix.infoseries.InfoSeries;
import com.example.nefix.series.Series;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Season
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seasonId;

    @ManyToOne()
    @JoinColumn(name = "seriesId", insertable = false, updatable = false)
    private Series series;

    @OneToMany()
    private Episode episode; // ERROR must be Many to one... WRONG ERD


    private Integer seasonNumber;
}
