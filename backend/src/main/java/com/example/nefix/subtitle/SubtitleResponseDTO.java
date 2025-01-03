package com.example.nefix.subtitle;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubtitleResponseDTO
{
    private Long subtitleId;
    private String language;
    private String subtitleLocation;

    public SubtitleResponseDTO(Subtitle subtitle)
    {
        this.subtitleId = subtitle.getSubtitleId();
        this.language = subtitle.getLanguage();
        this.subtitleLocation = subtitle.getSubtitleLocation();
    }
}
