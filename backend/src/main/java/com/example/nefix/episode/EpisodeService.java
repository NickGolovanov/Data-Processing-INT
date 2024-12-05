package com.example.nefix.episode;

import com.example.nefix.genrealization.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class EpisodeService extends BaseService<Episode, Long>
{
    public EpisodeService(EpisodeRepository repository)
    {
        super(repository);
    }
}
