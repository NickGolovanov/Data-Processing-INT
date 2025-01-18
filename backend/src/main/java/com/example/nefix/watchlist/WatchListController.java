package com.example.nefix.watchlist;

import com.example.nefix.genrealization.controller.BaseController;
import com.example.nefix.genrealization.response.ApiResponse;
import com.example.nefix.genrealization.response.ErrorResponse;
import com.example.nefix.movie.Movie;
import com.example.nefix.series.Series;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/watch-list")
public class WatchListController extends BaseController<WatchList, Long>
{
    @Autowired
    private WatchListService watchListService;

    public WatchListController(WatchListService service)
    {
        super(service);
    }

    @GetMapping("/profile/{profileId}/series")
    public ResponseEntity<ApiResponse<List<Series>>> getSeriesFromWatchList(@PathVariable Long profileId)
    {
        try
        {
            List<Series> series = this.watchListService.getSeriesFromWatchList(profileId);

            if (series.isEmpty())
            {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(new ApiResponse<>(series, null));
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, new ErrorResponse("Error creating entity: " + e.getMessage())));
        }
    }

    @PostMapping("/profile/{profileId}/series/{seriesId}")
    public ResponseEntity<ApiResponse<WatchList>> addSeriesToWatchList(@PathVariable Long profileId, @PathVariable Long seriesId)
    {
        try
        {
            WatchList watchList = this.watchListService.addSeriesToWatchList(profileId, seriesId);

            return ResponseEntity.ok(new ApiResponse<>(watchList, null));
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, new ErrorResponse("Error creating entity: " + e.getMessage())));
        }
    }

    @DeleteMapping("/profile/{profileId}/series/{seriesId}")
    public ResponseEntity<ApiResponse<Void>> removeSeriesFromWatchList(@PathVariable Long profileId, @PathVariable Long seriesId)
    {
        try
        {
            this.watchListService.removeSeriesFromWatchList(profileId, seriesId);

            return ResponseEntity.noContent().build();
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, new ErrorResponse("Error creating entity: " + e.getMessage())));
        }
    }

    @GetMapping("/profile/{profileId}/movies")
    public ResponseEntity<ApiResponse<List<Movie>>> getMoviesFromWatchList(@PathVariable Long profileId)
    {
        try
        {
            List<Movie> movies = this.watchListService.getMoviesFromWatchList(profileId);

            if (movies.isEmpty())
            {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(new ApiResponse<>(movies, null));
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, new ErrorResponse("Error creating entity: " + e.getMessage())));
        }
    }

    @PostMapping("/profile/{profileId}/movie/{movieId}")
    public ResponseEntity<ApiResponse<WatchList>> addMovieToWatchList(@PathVariable Long profileId, @PathVariable Long movieId)
    {
        try
        {
            WatchList watchList = this.watchListService.addMovieToWatchList(profileId, movieId);

            return ResponseEntity.ok(new ApiResponse<>(watchList, null));
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, new ErrorResponse("Error creating entity: " + e.getMessage())));
        }
    }

    @DeleteMapping("/profile/{profileId}/movie/{movieId}")
    public ResponseEntity<ApiResponse<Void>> removeMovieFromWatchList(@PathVariable Long profileId, @PathVariable Long movieId)
    {
        try
        {
            this.watchListService.removeMovieFromWatchList(profileId, movieId);

            return ResponseEntity.noContent().build();
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, new ErrorResponse("Error creating entity: " + e.getMessage())));
        }
    }
}
