package com.example.nefix.movie;

import com.example.nefix.genrealization.controller.BaseController;
import com.example.nefix.genrealization.response.ApiResponse;
import com.example.nefix.genrealization.response.ErrorResponse;
import com.example.nefix.info.Info;
import com.example.nefix.infomovie.InfoMovie;
import com.example.nefix.subtitle.Subtitle;
import com.example.nefix.subtitle.SubtitleResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movie")
public class MovieController extends BaseController<Movie, Long>
{
    @Autowired
    private MovieService movieService;

    public MovieController(MovieService service)
    {
        super(service);
    }

    @GetMapping("/general")
    public ResponseEntity<ApiResponse<List<MovieGeneralDto>>> getMovieGeneralDto()
    {
        try
        {
            List<MovieGeneralDto> movieGeneralDto = this.movieService.getMovieGeneralDto();

            if (movieGeneralDto.isEmpty())
            {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(new ApiResponse<>(movieGeneralDto, null));
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(null, new ErrorResponse(e.getMessage())));
        }
    }

    @GetMapping("/{movieId}/subtitle")
    public ResponseEntity<ApiResponse<List<SubtitleResponseDTO>>> getSubtitles(@PathVariable Long movieId)
    {
        try
        {
            List<SubtitleResponseDTO> subtitles = this.movieService.getSubtitles(movieId);

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

    @PostMapping("/{movieId}/subtitle")
    public ResponseEntity<ApiResponse<Subtitle>> addSubtitle(@PathVariable Long movieId, @Valid @RequestBody Subtitle subtitle)
    {
        try
        {
            Subtitle createdSubtitle = this.movieService.addSubtitle(movieId, subtitle);

            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(createdSubtitle, null));
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(null, new ErrorResponse(e.getMessage())));
        }
    }

    @GetMapping("/{movieId}/subtitle/{subtitleId}")
    public ResponseEntity<ApiResponse<Subtitle>> getSubtitle(@PathVariable Long movieId, @PathVariable Long subtitleId)
    {
        try
        {
            Subtitle subtitle = this.movieService.getSubtitle(movieId, subtitleId);

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

    @PutMapping("/{movieId}/subtitle/{subtitleId}")
    public ResponseEntity<ApiResponse<Subtitle>> updateSubtitle(@PathVariable Long movieId,
                                                                @PathVariable Long subtitleId,
                                                                @Valid @RequestBody Subtitle updatedSubtitle)
    {
        try
        {
            Subtitle subtitle = this.movieService.updateSubtitle(movieId, subtitleId, updatedSubtitle);

            return ResponseEntity.ok(new ApiResponse<>(subtitle, null));
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(null, new ErrorResponse(e.getMessage())));
        }
    }

    @DeleteMapping("/{movieId}/subtitle/{subtitleId}")
    public ResponseEntity<ApiResponse<Void>> deleteSubtitle(@PathVariable Long movieId, @PathVariable Long subtitleId)
    {
        try
        {
            this.movieService.deleteSubtitle(movieId, subtitleId);

            return ResponseEntity.noContent().build();
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(null, new ErrorResponse(e.getMessage())));
        }
    }

    @GetMapping("/{movieId}/info-movie")
    public ResponseEntity<ApiResponse<List<InfoMovie>>> getInfoMoviesByMovieId(@PathVariable Long movieId)
    {
        try
        {
            List<InfoMovie> infoMovies = this.movieService.getMovieInfoByMovieId(movieId);

            if (infoMovies.isEmpty())
            {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(new ApiResponse<>(infoMovies, null));
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(null, new ErrorResponse(e.getMessage())));
        }
    }

    @PostMapping("/{movieId}/info-movie")
    public ResponseEntity<ApiResponse<Info>> addInfoMovie(@PathVariable Long movieId, @Valid @RequestBody Info info)
    {
        try
        {
            Info createdInfo = this.movieService.addInfoMovie(movieId, info);

            return ResponseEntity.ok(new ApiResponse<>(createdInfo, null));
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(null, new ErrorResponse(e.getMessage())));
        }
    }

    @DeleteMapping("/{movieId}/info-movie/{infoId}")
    public ResponseEntity<ApiResponse<Void>> deleteInfoMovie(@PathVariable Long movieId, @PathVariable Long infoId)
    {
        try
        {
            this.movieService.deleteInfoMovie(movieId, infoId);

            return ResponseEntity.noContent().build();
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(null, new ErrorResponse(e.getMessage())));
        }
    }

    @PatchMapping("/{movieId}/info-movie/{infoId}")
    public ResponseEntity<ApiResponse<Info>> updateInfoMovie(@PathVariable Long movieId, @PathVariable Long infoId, @Valid @RequestBody Info info)
    {
        try
        {
            Info updatedInfo = this.movieService.updateInfoMovie(movieId, infoId, info);

            return ResponseEntity.ok(new ApiResponse<>(updatedInfo, null));
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(null, new ErrorResponse(e.getMessage())));
        }
    }
}
