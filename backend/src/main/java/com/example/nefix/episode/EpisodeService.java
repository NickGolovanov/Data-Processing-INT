package com.example.nefix.episode;

import com.example.nefix.genrealization.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpisodeService extends BaseService<Episode, Long>
{
    public EpisodeService(EpisodeRepository repository)
    {
        super(repository, List.of("episodeId"));
    }
}
