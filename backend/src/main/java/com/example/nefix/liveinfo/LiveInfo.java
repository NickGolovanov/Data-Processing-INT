package com.example.nefix.liveinfo;
import com.example.nefix.episode.Episode;
import com.example.nefix.movie.Movie;
import com.example.nefix.profile.Profile;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class LiveInfo
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    private String watchedTime;
}
