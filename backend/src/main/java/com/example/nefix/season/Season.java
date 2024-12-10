package com.example.nefix.season;

import com.example.nefix.episode.Episode;
import com.example.nefix.series.Series;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JoinColumn(name = "series_id", nullable = false, insertable = false, updatable = false)
    @JsonProperty("seriesId")
    private Series series;

    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL, orphanRemoval = false)
    private Set<Episode> episode;

    @JsonProperty("seasonNumber")
    @Column(name = "season_number")
    private Integer seasonNumber;
}
