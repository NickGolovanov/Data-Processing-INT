package com.example.nefix.episode;

import com.example.nefix.season.Season;
import com.example.nefix.subtitle.Subtitle;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Episode
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long episodeId;

    @ManyToOne()
    @JoinColumn(name = "seasonId", updatable = false, insertable = false)
    private Season season;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false, mappedBy = "subtitleId")
    private Set<Subtitle> subtitles;

    private String title;
    private Integer duration;
    private Integer views;
    private Boolean SD;
    private Boolean HD;
    private Boolean UHD;
}
