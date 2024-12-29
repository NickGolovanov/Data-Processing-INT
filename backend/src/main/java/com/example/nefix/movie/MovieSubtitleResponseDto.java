package com.example.nefix.movie;

import com.example.nefix.subtitle.Subtitle;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieSubtitleResponseDto {
    private Long subtitleId;
    private String language;
    private String subtitleLocation;

    public MovieSubtitleResponseDto(Long subtitleId, String language, String subtitleLocation) {
        this.subtitleId = subtitleId;
        this.language = language;
        this.subtitleLocation = subtitleLocation;
    }

    public MovieSubtitleResponseDto(Subtitle subtitle) {
        this.subtitleId = subtitle.getSubtitleId();
        this.language = subtitle.getLanguage();
        this.subtitleLocation = subtitle.getSubtitleLocation();

    }
}
