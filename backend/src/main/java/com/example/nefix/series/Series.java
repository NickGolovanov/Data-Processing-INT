package com.example.nefix.series;

import com.example.nefix.infoseries.InfoSeries;
import com.example.nefix.season.Season;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "series", uniqueConstraints = @UniqueConstraint(columnNames = {"title"}))
public class Series
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "series_id")
    private Long seriesId;

    @OneToMany(mappedBy = "series")
    @JsonIgnoreProperties({"series", "episodes"})
    private Set<Season> seasons = new HashSet<>();

    @OneToMany(mappedBy = "series", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("series")
    private Set<InfoSeries> infoSeries = new HashSet<>();

    @JsonProperty("title")
    @NotBlank(message = "Title must not be blank.")
    @NotNull(message = "Title must not be null.")
    private String title;

    @ColumnDefault("0")
    private Integer views;

    @JsonProperty("minimumAge")
    @Column(name = "minimum_age")
    @Min(value = 0, message = "Minimum age must not be negative.")
    private Integer minimumAge;
}
