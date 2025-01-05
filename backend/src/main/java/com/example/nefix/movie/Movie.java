package com.example.nefix.movie;

import com.example.nefix.infomovie.InfoMovie;
import com.example.nefix.subtitle.Subtitle;
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
@Table(name = "movie", uniqueConstraints = @UniqueConstraint(columnNames = {"title"}))
public class Movie
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long movieId;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonIgnoreProperties({"movie", "episode"})
    private Set<Subtitle> subtitles = new HashSet<>();

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonIgnoreProperties({"info"})
    private Set<InfoMovie> infoMovies = new HashSet<>();

    @JsonProperty("title")
    @NotBlank(message = "Title must not be blank.")
    @NotNull(message = "Title must not be null.")
    private String title;

    @JsonProperty("duration")
    @Min(value = 1, message = "Duration must be greater than 0.")
    @NotNull(message = "Duration must not be null.")
    private Integer duration;

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
