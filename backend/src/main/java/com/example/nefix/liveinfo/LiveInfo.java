package com.example.nefix.liveinfo;
import com.example.nefix.episode.Episode;
import com.example.nefix.movie.Movie;
import com.example.nefix.profile.Profile;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class LiveInfo
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "liveInfo_id")
    private Long liveInfoId;

    @ManyToOne(cascade = CascadeType.ALL) 
    @JoinColumn(name = "profileId", updatable = false, insertable = false)
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "episodeId", insertable = false, updatable = false)
    private Episode episode;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "movieId", insertable = false, updatable = false)
    private Movie movie;

    @JsonProperty("watchedTime")
    @Column(name = "watched_time")
    private String watchedTime;
}
