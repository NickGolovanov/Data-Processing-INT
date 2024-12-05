package com.example.nefix.series;

import com.example.nefix.genrealization.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class SeriesService extends BaseService<Series, Long> {
    public SeriesService(SeriesRepository repository) {
        super(repository);
    }
}
