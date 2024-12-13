package com.example.nefix.season;

import com.example.nefix.series.Series;
import com.example.nefix.series.SeriesRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SeasonDeserializer extends JsonDeserializer<Season>
{
    @Autowired
    private SeasonRepository seasonRepository;

    @Override
    public Season deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException
    {
        Long seasonId = jsonParser.getValueAsLong();

        return seasonRepository.findById(seasonId).orElseThrow(() -> new IllegalArgumentException("Season with ID " + seasonId + " not found"));
    }
}