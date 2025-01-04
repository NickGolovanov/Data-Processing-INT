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
    public ResponseEntity<Season> addSeason(@PathVariable Long seriesId, @PathVariable Long seasonId) {
        Season season = ((SeriesService) service).addSeason(seriesId, seasonId);
        return ResponseEntity.ok(season);
    }

    @DeleteMapping("/{seriesId}/seasons/{seasonId}")
    public ResponseEntity<Void> removeSeason(@PathVariable Long seriesId, @PathVariable Long seasonId) {
        ((SeriesService) service).removeSeason(seriesId, seasonId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{seriesId}/infoseries/{infoSeriesId}")
    public ResponseEntity<InfoSeries> addInfoSeries(@PathVariable Long seriesId, @PathVariable Long infoSeriesId) {
        InfoSeries infoSeries = ((SeriesService) service).addInfoSeries(seriesId, infoSeriesId);
        return ResponseEntity.ok(infoSeries);
    }

    @DeleteMapping("/{seriesId}/infoseries/{infoSeriesId}")
    public ResponseEntity<Void> removeInfoSeries(@PathVariable Long seriesId, @PathVariable Long infoSeriesId) {
        ((SeriesService) service).removeInfoSeries(seriesId, infoSeriesId);
        return ResponseEntity.noContent().build();
    }
}
