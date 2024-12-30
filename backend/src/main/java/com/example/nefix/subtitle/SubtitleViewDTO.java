package com.example.nefix.subtitle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubtitleViewDTO {

    private Long subtitleId;
    private String language;
    private String subtitleLocation;
    private Long episodeId;
    private String episodeTitle;
    private Long movieId;
    private String movieTitle;

    public SubtitleViewDTO(Long subtitleId, String language, String subtitleLocation, Long episodeId, String episodeTitle, Long movieId, String movieTitle) {
        this.subtitleId = subtitleId;
        this.language = language;
        this.subtitleLocation = subtitleLocation;
        this.episodeId = episodeId;
        this.episodeTitle = episodeTitle;
        this.movieId = movieId;
        this.movieTitle = movieTitle;
    }
}
