package com.example.nefix.season;

import com.example.nefix.episode.Episode;
import com.example.nefix.series.Series;
import com.example.nefix.series.SeriesDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
public class Season implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "season_id")
    private Long seasonId;

    @JsonProperty("seasonNumber")
    @Column(name = "season_number")
    @NotNull(message = "Season number must not be null.")
    @Min(value = 1, message = "Season number must be greater than 0.")
    private Integer seasonNumber;

    @ManyToOne
    @JoinColumn(name = "series_id", nullable = false)
    @JsonProperty(value = "seriesId", access = JsonProperty.Access.WRITE_ONLY)
    @JsonDeserialize(using = SeriesDeserializer.class)
    @NotNull(message = "Series must not be null.")
    private Series series;

    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonIgnoreProperties({"seasonId", "subtitles"})
    private Set<Episode> episodes;

    @JsonProperty("seriesId")
    public Long getSeriesId()
    {
        return this.series.getSeriesId();
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Season that = (Season) o;
        return Objects.equals(this.seasonId, that.seasonId);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.seasonId);
    }
}
