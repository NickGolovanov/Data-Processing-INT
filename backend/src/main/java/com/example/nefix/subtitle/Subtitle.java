package com.example.nefix.subtitle;

import com.example.nefix.episode.Episode;
import com.example.nefix.movie.Movie;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Subtitle
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subtitleId;

    @ManyToOne
    @JoinColumn(name = "episodeId", insertable = false, updatable = false)
    private Episode episode;

    @ManyToOne
    @JoinColumn(name = "movieId", insertable = false, updatable = false)
    private Movie movie;

    private String language;
    private String subtitleLocation;
}
