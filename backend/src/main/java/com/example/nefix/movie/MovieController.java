package com.example.nefix.movie;

import com.example.nefix.genrealization.controller.BaseController;
import com.example.nefix.info.Info;
import com.example.nefix.infomovie.InfoMovie;
import com.example.nefix.subtitle.Subtitle;
import com.example.nefix.subtitle.SubtitleResponseDTO;
import jakarta.validation.Valid;
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

    @GetMapping("/general")
    public ResponseEntity<?> getMovieGeneralDto(){
        try {
            return ResponseEntity.ok(((MovieService) service).getMovieGeneralDto());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
    @GetMapping("/{movieId}/subtitle")
    public ResponseEntity<?> getSubtitles(@PathVariable Long movieId) {
        try {
            List<SubtitleResponseDTO> subtitles = ((MovieService) service).getSubtitles(movieId);
            return ResponseEntity.ok(subtitles);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/{movieId}/subtitle")
    public ResponseEntity<?> addSubtitle(@PathVariable Long movieId, @Valid @RequestBody Subtitle subtitle) {
        try {
            Subtitle createdSubtitle = ((MovieService) service).addSubtitle(movieId, subtitle);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdSubtitle);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{movieId}/subtitle/{subtitleId}")
    public ResponseEntity<?> getSubtitle(@PathVariable Long movieId, @PathVariable Long subtitleId) {
        try {
            Subtitle subtitle = ((MovieService) service).getSubtitle(movieId, subtitleId);
            return ResponseEntity.ok(subtitle);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping("/{movieId}/subtitle/{subtitleId}")
    public ResponseEntity<?> updateSubtitle(@PathVariable Long movieId,
                                            @PathVariable Long subtitleId,
                                            @Valid @RequestBody Subtitle updatedSubtitle) {
        try {
            Subtitle subtitle = ((MovieService) service).updateSubtitle(movieId, subtitleId, updatedSubtitle);
            return ResponseEntity.ok(subtitle);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/{movieId}/subtitle/{subtitleId}")
    public ResponseEntity<?> deleteSubtitle(@PathVariable Long movieId, @PathVariable Long subtitleId) {
        try {

            ((MovieService) service).deleteSubtitle(movieId, subtitleId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{movieId}/info-movie")
    public ResponseEntity<?> getInfoMovieById(@PathVariable Long movieId) {
        try {
            return ResponseEntity.ok(((MovieService) service).getMovieInfoByMovieId(movieId));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/{movieId}/info-movie")
    public ResponseEntity<?> addInfoMovie(@PathVariable Long movieId, @Valid @RequestBody Info info) {
        try {
            return ResponseEntity.ok(((MovieService) service).addInfoMovie(movieId, info));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/{movieId}/info-movie/{infoId}")
    public ResponseEntity<?> deleteInfoMovie(@PathVariable Long movieId, @PathVariable Long infoId) {

        try {
            ((MovieService) service).deleteInfoMovie(movieId, infoId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PatchMapping("/{movieId}/info-movie/{infoId}")
    public ResponseEntity<?> updateInfoMovie(@PathVariable Long movieId, @PathVariable Long infoId, @Valid @RequestBody Info updatedInfo) {

        try {
            return ResponseEntity.ok(((MovieService) service).updateInfoMovie(movieId, infoId, updatedInfo));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
