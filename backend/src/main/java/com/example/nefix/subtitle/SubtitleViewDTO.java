package com.example.nefix.subtitle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SubtitleViewDTO {

    private Long subtitleId;
    private String language;
    private String subtitleLocation;
    private Long episodeId;
    private String episodeTitle;
    private Long movieId;
    private String movieTitle;
}
