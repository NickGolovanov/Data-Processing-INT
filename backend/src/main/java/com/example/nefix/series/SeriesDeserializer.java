package com.example.nefix.series;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SeriesDeserializer extends JsonDeserializer<Series>
{
    @Autowired
    private SeriesRepository seriesRepository;

    @Override
    public Series deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException
    {
        Long seriesId = jsonParser.getValueAsLong();

        return this.seriesRepository.findById(seriesId).orElseThrow(() -> new IllegalArgumentException("Series with ID " + seriesId + " not found"));
    }
}