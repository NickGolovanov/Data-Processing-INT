package com.example.nefix.season;

import com.example.nefix.episode.Episode;
import com.example.nefix.series.Series;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seasonId;

    @ManyToOne
    @JoinColumn(name = "series_id", referencedColumnName = "seriesId", nullable = false, insertable = false, updatable = false)
    private Series series;

    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL, orphanRemoval = false)
    private Set<Episode> episode; // Error was because the season has many episodes but was declared as a single episode


    private Integer seasonNumber;
}
