package com.example.nefix.watchlist;

import com.example.nefix.genrealization.controller.BaseController;
import com.example.nefix.series.Series;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/watch-list")
public class WatchListController extends BaseController<WatchList, Long> {
    public WatchListController(WatchListService service) {
        super(service);
    }

    @PostMapping("/profile/{profileId}/series/{seriesId}")
    public ResponseEntity<?> addSeriesToWatchList(@PathVariable Long profileId, @PathVariable Long seriesId) {
        try {
            WatchList watchList = ((WatchListService) service).addSeriesToWatchList(profileId, seriesId);
            return ResponseEntity.ok(watchList);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/profile/{profileId}")
    public ResponseEntity<?> getSeriesFromWatchList(@PathVariable Long profileId) {
        try {
            List<Series> seriesList = ((WatchListService) service).getSeriesFromWatchList(profileId);
            return ResponseEntity.ok(seriesList);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/{watchlistId}/series/{seriesId}")
    public ResponseEntity<?> removeSeriesFromWatchList(@PathVariable Long watchlistId, @PathVariable Long seriesId) {
        try {
            ((WatchListService) service).removeSeriesFromWatchList(watchlistId, seriesId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
