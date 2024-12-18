package com.example.nefix.liveinfo;

import com.example.nefix.episode.Episode;
import com.example.nefix.movie.Movie;
import com.example.nefix.profile.Profile;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class LiveInfo
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "liveinfo_id")
    private Long liveInfoId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id", updatable = false, insertable = false)
//    @JsonBackReference("profile-liveinfo")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "episode_id", nullable = true, insertable = false, updatable = false)
    private Episode episode;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "movie_id", nullable = true, insertable = false, updatable = false)
    private Movie movie;

    @JsonProperty("watchedTime")
    @Column(name = "watched_time")
    private String watchedTime;
}
