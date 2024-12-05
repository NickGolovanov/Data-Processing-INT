package com.example.nefix.subtitle;

import com.example.nefix.genrealization.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class SubtitleService extends BaseService<Subtitle, Long> {
    public SubtitleService(SubtitleRepository repository) {
        super(repository);
    }
}
