package com.example.nefix.series;

import com.example.nefix.genrealization.service.BaseService;
import com.example.nefix.info.Info;
import com.example.nefix.info.InfoRepository;
import com.example.nefix.infomovie.InfoMovie;
import com.example.nefix.infoseries.InfoSeries;
import com.example.nefix.infoseries.InfoSeriesRepository;
import com.example.nefix.season.Season;
import com.example.nefix.season.SeasonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeriesService extends BaseService<Series, Long>
{
    @Autowired
    private InfoSeriesRepository infoSeriesRepository;

    @Autowired
    private InfoRepository infoRepository;

    @Autowired
    private SeriesRepository seriesRepository;

    public SeriesService(SeriesRepository repository)
    {
        super(repository, List.of("seriesId"));
    }

    public List<SeriesGeneralDto> getSeriesGeneralDto()
    {
        return this.seriesRepository.findAll().stream()
                .map(SeriesGeneralDto::new)
                .toList();
    }

    public List<InfoSeries> getSeriesInfoBySeriesId(Long seriesId)
    {
        return infoSeriesRepository.findAllBySeries_SeriesId(seriesId);
    }

    @Transactional
    public Info addInfoSeries(Long seriesId, Info info)
    {
        long infoId = this.infoSeriesRepository.callAddInfoSeries(
                seriesId,
                info.getDescription(),
                info.getType().toString()
        );
        return this.infoRepository.findById(infoId).orElseThrow(() -> new RuntimeException("Info not found"));
    }

    @Transactional
    public void deleteInfoSeries(Long seriesId, Long infoId)
    {
        this.infoSeriesRepository.callDeleteInfoSeries(seriesId, infoId);
    }

    @Transactional
    public Info updateInfoSeries(Long seriesId, Long infoId, Info updatedInfo)
    {
        this.infoSeriesRepository.callUpdateInfoSeries(
                infoId,
                seriesId,
                updatedInfo.getDescription(),
                updatedInfo.getType().toString()
        );

        return this.infoRepository.findById(infoId).orElseThrow(() -> new RuntimeException("InfoMovie not found"));
    }
}
