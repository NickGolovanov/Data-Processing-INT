package com.example.nefix.movie;

import com.example.nefix.genrealization.service.BaseService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class MovieService extends BaseService<Movie, Long>
{
    public MovieService(MovieRepository repository)
    {
        super(repository);
    }
}
