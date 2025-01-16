package com.example.nefix.series;

import com.example.nefix.blockedaccount.BlockedAccount;
import com.example.nefix.infoseries.InfoSeries;
import com.example.nefix.season.Season;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
public class Series implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "series_id")
    private Long seriesId;

    @JsonProperty("title")
    @NotBlank(message = "Title must not be blank.")
    private String title;

    @JsonProperty("views")
    @ColumnDefault("0")
    private Integer views;

    @JsonProperty("minimumAge")
    @Column(name = "minimum_age")
    @Min(value = 0, message = "Minimum age must not be negative.")
    private Integer minimumAge;

    @OneToMany(mappedBy = "series")
    @JsonIgnoreProperties({"seriesId", "episodes"})
    private Set<Season> seasons = new HashSet<>();

    @OneToMany(mappedBy = "series", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"seriesId"})
    private Set<InfoSeries> infos = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Series that = (Series) o;
        return Objects.equals(this.seriesId, that.seriesId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.seriesId);
    }
}
