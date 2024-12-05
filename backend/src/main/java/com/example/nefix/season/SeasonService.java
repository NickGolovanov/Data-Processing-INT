package com.example.nefix.season;

import com.example.nefix.genrealization.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class SeasonService extends BaseService<Season, Long>{
    public SeasonService(SeasonRepository repository) {
        super(repository);
    }
}
