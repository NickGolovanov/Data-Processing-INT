package com.example.nefix.liveinfo;

import com.example.nefix.episode.Episode;
import com.example.nefix.movie.Movie;
import com.example.nefix.profile.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
@Entity
public class LiveInfo implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "liveinfo_id")
    private Long liveInfoId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id", updatable = false, insertable = false)
    @JsonIgnore
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "episode_id", nullable = true, insertable = false, updatable = false)
    @JsonIgnore
    private Episode episode;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "movie_id", nullable = true, insertable = false, updatable = false)
    @JsonIgnore
    private Movie movie;

    @JsonProperty("watchedTime")
    @Column(name = "watched_time")
    private String watchedTime;

    @JsonProperty("profileId")
    public Long getProfileId()
    {
        return this.profile.getProfileId();
    }

    @JsonProperty("episodeId")
    public Long getEpisodeId()
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
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        LiveInfo that = (LiveInfo) o;
        return Objects.equals(this.liveInfoId, that.liveInfoId);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.liveInfoId);
    }
}
