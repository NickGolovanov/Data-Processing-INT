package com.example.nefix.series;

import com.example.nefix.genrealization.controller.BaseController;
import com.example.nefix.infoseries.InfoSeries;
import com.example.nefix.season.Season;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/series")
public class SeriesController extends BaseController<Series, Long> {
    public SeriesController(SeriesService service) {
        super(service);
    }

    @PostMapping("/{seriesId}/seasons/{seasonId}")
    public ResponseEntity<?> addSeason(@PathVariable Long seriesId, @PathVariable Long seasonId) {
        try {
            Season season = ((SeriesService) service).addSeason(seriesId, seasonId);
            return ResponseEntity.ok(season);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/{seriesId}/seasons/{seasonId}")
    public ResponseEntity<?> removeSeason(@PathVariable Long seriesId, @PathVariable Long seasonId) {
        try {
            ((SeriesService) service).removeSeason(seriesId, seasonId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/{seriesId}/infoseries/{infoSeriesId}")
    public ResponseEntity<?> addInfoSeries(@PathVariable Long seriesId, @PathVariable Long infoSeriesId) {
        try {
            InfoSeries infoSeries = ((SeriesService) service).addInfoSeries(seriesId, infoSeriesId);
            return ResponseEntity.ok(infoSeries);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/{seriesId}/infoseries/{infoSeriesId}")
    public ResponseEntity<?> removeInfoSeries(@PathVariable Long seriesId, @PathVariable Long infoSeriesId) {
        try {
            ((SeriesService) service).removeInfoSeries(seriesId, infoSeriesId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
