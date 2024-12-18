package com.example.nefix.episode;

import com.example.nefix.movie.Movie;
import com.example.nefix.movie.MovieRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EpisodeDeserializer extends JsonDeserializer<Episode>
{
    @Autowired
    private EpisodeRepository episodeRepository;

    @Override
    public Episode deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException
    {
        Long episodeId = jsonParser.getValueAsLong();

        return episodeRepository.findById(episodeId).orElseThrow(() -> new IllegalArgumentException("Episode with ID " + episodeId + " not found"));
    }
}