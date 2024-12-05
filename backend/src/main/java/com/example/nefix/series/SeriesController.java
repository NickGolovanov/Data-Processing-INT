package com.example.nefix.series;

import com.example.nefix.genrealization.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/series")
public class SeriesController extends BaseController<Series, Long> {
    public SeriesController(SeriesService service) {
        super(service);
    }
}
