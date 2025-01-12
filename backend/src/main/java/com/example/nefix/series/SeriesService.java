package com.example.nefix.series;

import com.example.nefix.genrealization.service.BaseService;
import com.example.nefix.movie.MovieGeneralDto;
import com.example.nefix.season.Season;
import com.example.nefix.season.SeasonRepository;
import com.example.nefix.infoseries.InfoSeries;
import com.example.nefix.infoseries.InfoSeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeriesService extends BaseService<Series, Long> {
    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private InfoSeriesRepository infoSeriesRepository;

    public SeriesService(SeriesRepository repository) {
        super(repository);
    }

    public List<SeriesGeneralDto> getSeriesGeneralDto() {
        return repository.findAll().stream()
                .map(SeriesGeneralDto::new)
                .toList();
    }


    public Season addSeason(Long seriesId, Long seasonId) {
        // Call stored procedure through repository
        seasonRepository.addSeasonToSeries(seriesId, seasonId);
        return null;
    }

    public void removeSeason(Long seriesId, Long seasonId) {
        Season season = seasonRepository.findById(seasonId)
                .filter(s -> s.getSeries().getSeriesId().equals(seriesId))
                .orElseThrow(() -> new RuntimeException("Season not found or doesn't belong to Series"));
        seasonRepository.delete(season);
    }

    public InfoSeries addInfoSeries(Long seriesId, Long infoId) {
        // Call stored procedure through repository
        infoSeriesRepository.addInfoToSeries(seriesId, infoId);
        return null;
    }

    public void removeInfoSeries(Long seriesId, Long infoSeriesId) {
        InfoSeries infoSeries = infoSeriesRepository.findBySeries_SeriesIdAndInfo_InfoId(seriesId, infoSeriesId)
                .orElseThrow(() -> new RuntimeException("InfoSeries not found or doesn't belong to Series"));
        infoSeriesRepository.delete(infoSeries);
    }
}
