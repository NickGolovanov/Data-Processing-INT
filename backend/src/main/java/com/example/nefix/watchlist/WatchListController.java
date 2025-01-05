package com.example.nefix.watchlist;

import com.example.nefix.genrealization.controller.BaseController;
import com.example.nefix.series.Series;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/watch-list")
public class WatchListController extends BaseController<WatchList, Long> {
    public WatchListController(WatchListService service) {
        super(service);
    }

    @PostMapping("/profile/{profileId}/series/{seriesId}")
    public ResponseEntity<WatchList> addSeriesToWatchList(@PathVariable Long profileId, @PathVariable Long seriesId) {
        WatchList watchList = ((WatchListService) service).addSeriesToWatchList(profileId, seriesId);
        return ResponseEntity.ok(watchList);
    }

    @GetMapping("/profile/{profileId}")
    public ResponseEntity<List<Series>> getSeriesFromWatchList(@PathVariable Long profileId) {
        List<Series> seriesList = ((WatchListService) service).getSeriesFromWatchList(profileId);
        return ResponseEntity.ok(seriesList);
    }

    @DeleteMapping("/{watchlistId}/series/{seriesId}")
    public ResponseEntity<Void> removeSeriesFromWatchList(@PathVariable Long watchlistId, @PathVariable Long seriesId) {
        ((WatchListService) service).removeSeriesFromWatchList(watchlistId, seriesId);
        return ResponseEntity.noContent().build();
    }
}
