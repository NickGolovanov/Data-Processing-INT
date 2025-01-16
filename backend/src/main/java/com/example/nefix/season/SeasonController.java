package com.example.nefix.season;

import com.example.nefix.genrealization.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/season")
public class SeasonController extends BaseController<Season, Long>{
    public SeasonController(SeasonService service) {
        super(service);
    }
}
