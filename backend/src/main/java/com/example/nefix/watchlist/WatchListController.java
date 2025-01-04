package com.example.nefix.watchlist;

import com.example.nefix.genrealization.controller.BaseController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/watch-list")
public class WatchListController extends BaseController<WatchList, Long> {
    public WatchListController(WatchListService service) {
        super(service);
    }

    @PostMapping("/{watchlistId}/series/{seriesId}")
    public ResponseEntity<WatchList> addSeriesToWatchList(@PathVariable Long watchlistId, @PathVariable Long seriesId) {
        WatchList watchList = ((WatchListService) service).addSeriesToWatchList(watchlistId, seriesId);
        return ResponseEntity.ok(watchList);
    }

    @GetMapping("/{watchlistId}/series/{seriesId}")
    public ResponseEntity<WatchList> getSeriesFromWatchList(@PathVariable Long watchlistId, @PathVariable Long seriesId) {
        WatchList watchList = ((WatchListService) service).getSeriesFromWatchList(watchlistId, seriesId);
        return ResponseEntity.ok(watchList);
    }

    @DeleteMapping("/{watchlistId}/series/{seriesId}")
    public ResponseEntity<Void> removeSeriesFromWatchList(@PathVariable Long watchlistId, @PathVariable Long seriesId) {
        ((WatchListService) service).removeSeriesFromWatchList(watchlistId, seriesId);
        return ResponseEntity.noContent().build();
    }
}
