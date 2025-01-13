package com.example.nefix.subtitle;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubtitleResponseDTO
{
    @NotNull(message = "Subtitle ID cannot be null")
    private Long subtitleId;
    @NotNull(message = "Language cannot be null")
    private String language;
    @NotNull(message = "Subtitle location cannot be null")
    private String subtitleLocation;

    public SubtitleResponseDTO(Subtitle subtitle)
    {
        this.subtitleId = subtitle.getSubtitleId();
        this.language = subtitle.getLanguage();
        this.subtitleLocation = subtitle.getSubtitleLocation();
    }
}
