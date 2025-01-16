package com.example.nefix.movie;

import com.example.nefix.blockedaccount.BlockedAccount;
import com.example.nefix.infomovie.InfoMovie;
import com.example.nefix.subtitle.Subtitle;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
public class Movie implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long movieId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("duration")
    private Integer duration;

    @JsonProperty("views")
    private Integer views;

    @JsonProperty("SD")
    private Boolean SD;

    @JsonProperty("HD")
    private Boolean HD;

    @JsonProperty("UHD")
    private Boolean UHD;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonIgnoreProperties({"episodeId", "movieId"})
    private Set<Subtitle> subtitles = new HashSet<>();

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonIgnoreProperties({"movieId"})
    private Set<InfoMovie> infos = new HashSet<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie that = (Movie) o;
        return Objects.equals(this.movieId, that.movieId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.movieId);
    }
}
