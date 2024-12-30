package com.example.nefix.movie;

import com.example.nefix.genrealization.controller.BaseController;
import com.example.nefix.subtitle.Subtitle;
import com.example.nefix.subtitle.SubtitleViewDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController extends BaseController<Movie, Long> {
    public MovieController(MovieService service) {
        super(service);
    }

    @PostMapping("/{movieId}/subtitle")
    public ResponseEntity<Subtitle> addSubtitle(@PathVariable Long movieId, @RequestBody Subtitle subtitle) {
        Subtitle createdSubtitle = ((MovieService) service).addSubtitle(movieId, subtitle);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSubtitle);
    }


    @GetMapping("/with-subtitle")
    public ResponseEntity<List<SubtitleViewDTO>> getAllSubtitles() {
        return ResponseEntity.ok( ((MovieService) service).getMovieWithSubtitles());
    }

    @GetMapping("/{movieId}/subtitle")
    public ResponseEntity<List<MovieSubtitleResponseDto>> getSubtitle(@PathVariable Long movieId) {
        System.out.println("movieId = " + movieId);
        List<MovieSubtitleResponseDto> subtitles = ((MovieService) service).getSubtitles(movieId);
        return ResponseEntity.ok(subtitles);
    }

    @GetMapping("/{movieId}/subtitle/{subtitleId}")
    public ResponseEntity<Subtitle> getSubtitle(@PathVariable Long movieId, @PathVariable Long subtitleId) {
        Subtitle subtitle = ((MovieService) service).getSubtitle(movieId, subtitleId);
        return ResponseEntity.ok(subtitle);
    }

    @PatchMapping("/{movieId}/subtitle/{subtitleId}")
    public ResponseEntity<Subtitle> updateSubtitle(@PathVariable Long movieId,
                                                   @PathVariable Long subtitleId,
                                                   @RequestBody Subtitle updatedSubtitle) {
        Subtitle subtitle = ((MovieService) service).updateSubtitle(movieId, subtitleId, updatedSubtitle);
        return ResponseEntity.ok(subtitle);
    }

    @DeleteMapping("/{movieId}/subtitle/{subtitleId}")
    public ResponseEntity<Void> deleteSubtitle(@PathVariable Long movieId, @PathVariable Long subtitleId) {
        ((MovieService) service).deleteSubtitle(movieId, subtitleId);
        return ResponseEntity.noContent().build();
    }

}
