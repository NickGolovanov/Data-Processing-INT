package com.example.nefix.subtitle;

import com.example.nefix.genrealization.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/subtitle")
public class SubtitleController extends BaseController<Subtitle, Long> {
    public SubtitleController(SubtitleService service) {
        super(service);
    }
}
