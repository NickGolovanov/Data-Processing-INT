package com.example.nefix.episode;

import com.example.nefix.genrealization.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/episode")
public class EpisodeController extends BaseController<Episode, Long>
{
    public EpisodeController(EpisodeService service)
    {
        super(service);
    }
}
