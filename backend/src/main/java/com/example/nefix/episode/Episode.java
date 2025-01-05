package com.example.nefix.episode;

import com.example.nefix.season.Season;
import com.example.nefix.season.SeasonDeserializer;
import com.example.nefix.series.SeriesDeserializer;
import com.example.nefix.subtitle.Subtitle;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DialectOverride;

import java.util.Set;

@Data
@Entity
@Table(name = "episode", indexes = {@Index(columnList = "season_id, title")})
public class Episode
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "episode_id")
    private Long episodeId;

    @ManyToOne
    @JoinColumn(name = "season_id", nullable = false)
    @JsonProperty("seasonId")
    @JsonDeserialize(using = SeasonDeserializer.class)
    @JsonIgnoreProperties({"episodes", "series"})
    @NotNull(message = "Season must not be null.")
    private Season season;

    @OneToMany(mappedBy = "episode", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonIgnoreProperties({"movie", "episode"})
    private Set<Subtitle> subtitles;

    @JsonProperty("title")
    @NotBlank(message = "Title must not be blank.")
    private String title;

    @JsonProperty("duration")
    @Min(value = 1, message = "Duration must be greater than 0.")
    @NotNull(message = "Duration must not be null.")
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
