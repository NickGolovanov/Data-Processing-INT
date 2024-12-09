package com.example.nefix.episode;

import com.example.nefix.season.Season;
import com.example.nefix.subtitle.Subtitle;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

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
    private Integer views;

    @JsonProperty("SD")
    private Boolean SD;

    @JsonProperty("HD")
    private Boolean HD;

    @JsonProperty("UHD")
    private Boolean UHD;
}
