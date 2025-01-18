package com.example.nefix.season;

import com.example.nefix.genrealization.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeasonService extends BaseService<Season, Long>
{
    public SeasonService(SeasonRepository repository)
    {
        super(repository, List.of("seasonId"));
    }
}
