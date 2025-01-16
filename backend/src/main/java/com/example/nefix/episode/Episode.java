package com.example.nefix.episode;

import com.example.nefix.season.Season;
import com.example.nefix.season.SeasonDeserializer;
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

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
public class Episode implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "episode_id")
    private Long episodeId;

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

    @ManyToOne
    @JoinColumn(name = "season_id", nullable = false)
    @JsonProperty(value = "seasonId", access = JsonProperty.Access.WRITE_ONLY)
    @JsonDeserialize(using = SeasonDeserializer.class)
    @NotNull(message = "Season must not be null.")
    private Season season;

    @OneToMany(mappedBy = "episode", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonIgnoreProperties({"episodeId", "movieId"})
    private Set<Subtitle> subtitles = new HashSet<>();

    @JsonProperty("seasonId")
    public Long getSeasonId()
    {
        return this.season.getSeasonId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Episode that = (Episode) o;
        return Objects.equals(this.episodeId, that.episodeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.episodeId);
    }
}
