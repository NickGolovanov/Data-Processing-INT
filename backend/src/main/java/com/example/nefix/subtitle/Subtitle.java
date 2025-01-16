package com.example.nefix.subtitle;

import com.example.nefix.blockedaccount.BlockedAccount;
import com.example.nefix.episode.Episode;
import com.example.nefix.episode.EpisodeDeserializer;
import com.example.nefix.movie.Movie;
import com.example.nefix.movie.MovieDeserializer;
import com.example.nefix.series.SeriesDeserializer;
import com.example.nefix.subscription.Subscription;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.context.annotation.Lazy;

import java.io.Serializable;
import java.util.Objects;

@Data
@Entity
public class Subtitle implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subtitle_id")
    private Long subtitleId;

    @JsonProperty("language")
    @NotBlank(message = "Language cannot be blank")
    @NotBlank(message = "Language must not be blank.")
    private String language;

    @JsonProperty("subtitleLocation")
    @Column(name = "subtitle_location")
    @NotBlank(message = "Subtitle location cannot be blank")
    private String subtitleLocation;

    @ManyToOne
    @JoinColumn(name = "episode_id")
    @JsonProperty(value = "episodeId", access = JsonProperty.Access.WRITE_ONLY)
    @JsonDeserialize(using = EpisodeDeserializer.class)
    private Episode episode;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    @JsonProperty(value = "movieId", access = JsonProperty.Access.WRITE_ONLY)
    @JsonDeserialize(using = MovieDeserializer.class)
    private Movie movie;

    @JsonProperty("episodeId")
    public Long getSeasonId()
    {
        if (this.episode == null)
        {
            return null;
        }
        return this.episode.getEpisodeId();
    }

    @JsonProperty("movieId")
    public Long getMovieId()
    {
        if (this.movie == null)
        {
            return null;
        }
        return this.movie.getMovieId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subtitle that = (Subtitle) o;
        return Objects.equals(this.subtitleId, that.subtitleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.subtitleId);
    }
}
