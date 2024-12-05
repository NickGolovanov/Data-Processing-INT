package com.example.nefix.movie;

import com.example.nefix.genrealization.controller.BaseController;
import com.example.nefix.genrealization.service.ServiceBase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController extends BaseController<Movie, Long>
{
    public MovieController(MovieService service)
    {
        super(service);
    }
}
