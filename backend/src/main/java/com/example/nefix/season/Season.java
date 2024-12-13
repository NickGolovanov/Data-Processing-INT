package com.example.nefix.season;

import com.example.nefix.account.AccountDeserializer;
import com.example.nefix.episode.Episode;
import com.example.nefix.series.Series;
import com.example.nefix.series.SeriesDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Season
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "season_id")
    private Long seasonId;

    @ManyToOne
    @JoinColumn(name = "series_id", nullable = false)
    @JsonProperty("seriesId")
    @JsonDeserialize(using = SeriesDeserializer.class)
    @JsonIgnoreProperties({"seasons", "infoSeries"})
    private Series series;

    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonIgnoreProperties({"season", "series"})
    private Set<Episode> episode;

    @JsonProperty("seasonNumber")
    @Column(name = "season_number")
    private Integer seasonNumber;
}
