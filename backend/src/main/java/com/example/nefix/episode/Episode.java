package com.example.nefix.episode;

import com.example.nefix.season.Season;
import com.example.nefix.subtitle.Subtitle;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DialectOverride;

import java.util.Set;

@Data
@Entity
public class Episode
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "episode_id")
    private Long episodeId;

    @ManyToOne
    @JoinColumn(name = "season_id", referencedColumnName = "seasonId", nullable = false, updatable = false, insertable = false)
    @JsonProperty("seasonId")
    private Season season;

    @OneToMany(mappedBy = "episode", cascade = CascadeType.ALL, orphanRemoval = false)
    private Set<Subtitle> subtitles;

    @JsonProperty("title")
    private String title;

    @JsonProperty("duration")
    private Double duration;

    @JsonProperty("views")
    @ColumnDefault("0")
    private Integer views;

    @JsonProperty("SD")
    @ColumnDefault("false")
    private Boolean SD;

    @JsonProperty("HD")
    @ColumnDefault("false")
    private Boolean HD;

    @JsonProperty("UHD")
    @ColumnDefault("false")
    private Boolean UHD;
}
