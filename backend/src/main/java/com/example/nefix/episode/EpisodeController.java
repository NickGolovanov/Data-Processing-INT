package com.example.nefix.episode;

import com.example.nefix.genrealization.controller.BaseController;
import com.example.nefix.genrealization.response.ApiResponse;
import com.example.nefix.genrealization.response.ErrorResponse;
import com.example.nefix.subtitle.Subtitle;
import com.example.nefix.subtitle.SubtitleResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/episode")
public class EpisodeController extends BaseController<Episode, Long>
{
    @Autowired
    private EpisodeService episodeService;

    public EpisodeController(EpisodeService service)
    {
        super(service);
    }

    @GetMapping("/{episodeId}/subtitle")
    public ResponseEntity<ApiResponse<List<SubtitleResponseDTO>>> getSubtitles(@PathVariable Long episodeId)
    {
        try
        {
            List<SubtitleResponseDTO> subtitles = this.episodeService.getSubtitles(episodeId);

            if (subtitles.isEmpty())
            {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(new ApiResponse<>(subtitles, null));
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(null, new ErrorResponse(e.getMessage())));
        }
    }

    @PostMapping("/{episodeId}/subtitle")
    public ResponseEntity<ApiResponse<Subtitle>> addSubtitle(@PathVariable Long episodeId, @Valid @RequestBody Subtitle subtitle)
    {
        try
        {
            Subtitle createdSubtitle = this.episodeService.addSubtitle(episodeId, subtitle);

            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(createdSubtitle, null));
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(null, new ErrorResponse(e.getMessage())));
        }
    }

    @GetMapping("/{episodeId}/subtitle/{subtitleId}")
    public ResponseEntity<ApiResponse<Subtitle>> getSubtitle(@PathVariable Long episodeId, @PathVariable Long subtitleId)
    {
        try
        {
            Subtitle subtitle = this.episodeService.getSubtitle(episodeId, subtitleId);

            if (subtitle == null)
            {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(new ApiResponse<>(subtitle, null));
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(null, new ErrorResponse(e.getMessage())));
        }
    }

    @PutMapping("/{episodeId}/subtitle/{subtitleId}")
    public ResponseEntity<ApiResponse<Subtitle>> updateSubtitle(@PathVariable Long episodeId,
                                                                @PathVariable Long subtitleId,
                                                                @Valid @RequestBody Subtitle updatedSubtitle)
    {
        try
        {
            Subtitle subtitle = this.episodeService.updateSubtitle(episodeId, subtitleId, updatedSubtitle);

            return ResponseEntity.ok(new ApiResponse<>(subtitle, null));
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(null, new ErrorResponse(e.getMessage())));
        }
    }

    @DeleteMapping("/{episodeId}/subtitle/{subtitleId}")
    public ResponseEntity<ApiResponse<Void>> deleteSubtitle(@PathVariable Long episodeId, @PathVariable Long subtitleId)
    {
        try
        {
            this.episodeService.deleteSubtitle(episodeId, subtitleId);

            return ResponseEntity.noContent().build();
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(null, new ErrorResponse(e.getMessage())));
        }
    }
}
