package com.example.nefix.movie;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MovieDeserializer extends JsonDeserializer<Movie>
{
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Movie deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException
    {
        Long movieId = jsonParser.getValueAsLong();

        return this.movieRepository.findById(movieId).orElseThrow(() -> new IllegalArgumentException("Movie with ID " + movieId + " not found"));
    }
}