package com.example.nefix.series;

import com.example.nefix.genrealization.service.BaseService;
import com.example.nefix.season.Season;
import com.example.nefix.season.SeasonRepository;
import com.example.nefix.infoseries.InfoSeries;
import com.example.nefix.infoseries.InfoSeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeriesService extends BaseService<Series, Long> {
    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private InfoSeriesRepository infoSeriesRepository;

    public SeriesService(SeriesRepository repository) {
        super(repository);
    }

    public Season addSeason(Long seriesId, Long seasonId) {
        Series series = repository.findById(seriesId)
                .orElseThrow(() -> new RuntimeException("Series not found with ID: " + seriesId));
        Season season = seasonRepository.findById(seasonId)
                .orElseThrow(() -> new RuntimeException("Season not found with ID: " + seasonId));

        season.setSeries(series);
        return seasonRepository.save(season);
    }

    public void removeSeason(Long seriesId, Long seasonId) {
        Season season = seasonRepository.findById(seasonId)
                .filter(s -> s.getSeries().getSeriesId().equals(seriesId))
                .orElseThrow(() -> new RuntimeException("Season not found or doesn't belong to Series"));
        seasonRepository.delete(season);
    }

    public InfoSeries addInfoSeries(Long seriesId, Long infoSeriesId) {
        Series series = repository.findById(seriesId)
                .orElseThrow(() -> new RuntimeException("Series not found with ID: " + seriesId));
        InfoSeries infoSeries = infoSeriesRepository.findById(infoSeriesId)
                .orElseThrow(() -> new RuntimeException("InfoSeries not found with ID: " + infoSeriesId));

        infoSeries.setSeries(series);
        return infoSeriesRepository.save(infoSeries);
    }

    public void removeInfoSeries(Long seriesId, Long infoSeriesId) {
        InfoSeries infoSeries = infoSeriesRepository.findBySeries_SeriesIdAndInfo_InfoId(seriesId, infoSeriesId)
                .orElseThrow(() -> new RuntimeException("InfoSeries not found or doesn't belong to Series"));
        infoSeriesRepository.delete(infoSeries);
    }
}
