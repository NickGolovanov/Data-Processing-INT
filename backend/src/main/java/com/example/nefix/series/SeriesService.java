package com.example.nefix.series;

import com.example.nefix.genrealization.service.BaseService;
import com.example.nefix.movie.MovieGeneralDto;
import com.example.nefix.referraldiscount.ReferralDiscount;
import com.example.nefix.season.Season;
import com.example.nefix.season.SeasonRepository;
import com.example.nefix.infoseries.InfoSeries;
import com.example.nefix.infoseries.InfoSeriesRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeriesService extends BaseService<Series, Long> {
    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private InfoSeriesRepository infoSeriesRepository;

    @Autowired
    private SeriesRepository seriesRepository;

    public SeriesService(SeriesRepository repository) {
        super(repository, List.of("seriesId"));
    }

    public List<SeriesGeneralDto> getSeriesGeneralDto() {
        return repository.findAll().stream()
                .map(SeriesGeneralDto::new)
                .toList();
    }


    public Season addSeason(Long seriesId, Long seasonId) {
        // Call stored procedure through repository
        seasonRepository.addSeasonToSeries(seriesId, seasonId);
        // Retrieve the series from the database, then return the newly added season
        return seriesRepository.findById(seriesId)
                .map(series -> series.getSeasons().stream()
                        .filter(season -> season.getSeasonId().equals(seasonId))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Season not found")))
                .orElseThrow(() -> new RuntimeException("Series not found"));
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
        return infoSeriesRepository.findBySeries_SeriesIdAndInfo_InfoId(seriesId, infoId);
    }

    public void removeInfoSeries(Long seriesId, Long infoId) {
        // Find the InfoSeries entity by seriesId and infoId
        InfoSeries infoSeries = infoSeriesRepository.findBySeries_SeriesIdAndInfo_InfoId(seriesId, infoId);

        if (infoSeries == null) {
            throw new RuntimeException("InfoSeries not found or doesn't belong to the specified Series");
        }

        // Delete the InfoSeries entity
        infoSeriesRepository.delete(infoSeries);
    }
}
