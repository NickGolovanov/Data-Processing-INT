package com.example.nefix.movie;

import com.example.nefix.genrealization.controller.BaseController;
import com.example.nefix.info.Info;
import com.example.nefix.infomovie.InfoMovie;
import com.example.nefix.subtitle.Subtitle;
import com.example.nefix.subtitle.SubtitleResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController extends BaseController<Movie, Long>
{
    public MovieController(MovieService service)
    {
        super(service);
    }

    @GetMapping("/{movieId}/subtitle")
    public ResponseEntity<List<SubtitleResponseDTO>> getSubtitles(@PathVariable Long movieId)
    {
        List<SubtitleResponseDTO> subtitles = ((MovieService) service).getSubtitles(movieId);
        return ResponseEntity.ok(subtitles);
    }

    @PostMapping("/{movieId}/subtitle")
    public ResponseEntity<Subtitle> addSubtitle(@PathVariable Long movieId, @RequestBody Subtitle subtitle)
    {
        Subtitle createdSubtitle = ((MovieService) service).addSubtitle(movieId, subtitle);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSubtitle);
    }

    @GetMapping("/{movieId}/subtitle/{subtitleId}")
    public ResponseEntity<Subtitle> getSubtitle(@PathVariable Long movieId, @PathVariable Long subtitleId)
    {
        Subtitle subtitle = ((MovieService) service).getSubtitle(movieId, subtitleId);
        return ResponseEntity.ok(subtitle);
    }

    @PutMapping("/{movieId}/subtitle/{subtitleId}")
    public ResponseEntity<Subtitle> updateSubtitle(@PathVariable Long movieId,
                                                   @PathVariable Long subtitleId,
                                                   @RequestBody Subtitle updatedSubtitle)
    {
        Subtitle subtitle = ((MovieService) service).updateSubtitle(movieId, subtitleId, updatedSubtitle);
        return ResponseEntity.ok(subtitle);
    }

    @DeleteMapping("/{movieId}/subtitle/{subtitleId}")
    public ResponseEntity<Void> deleteSubtitle(@PathVariable Long movieId, @PathVariable Long subtitleId)
    {
        ((MovieService) service).deleteSubtitle(movieId, subtitleId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{movieId}/info-movie")
    public ResponseEntity<List<InfoMovie>> getInfoMovieById(@PathVariable Long movieId)
    {
        return ResponseEntity.ok(((MovieService) service).getMovieInfoByMovieId(movieId));
    }

    @PostMapping("/{movieId}/info-movie")
    public ResponseEntity<InfoMovie> addInfoMovie(@PathVariable Long movieId, @RequestBody Info info)
    {
        return ResponseEntity.ok(((MovieService) service).addInfoMovie(movieId, info));
    }

    @DeleteMapping("/{movieId}/info-movie/{infoId}")
    public ResponseEntity<Void> deleteInfoMovie(@PathVariable Long movieId, @PathVariable Long infoId)
    {
        ((MovieService) service).deleteInfoMovie(movieId, infoId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{movieId}/info-movie/{infoId}")
    public ResponseEntity<InfoMovie> updateInfoMovie(@PathVariable Long movieId, @PathVariable Long infoId, @RequestBody Info updatedInfo)
    {
        return ResponseEntity.ok(((MovieService) service).updateInfoMovie(movieId, infoId, updatedInfo));
    }
}
