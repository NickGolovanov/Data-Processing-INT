package com.example.nefix.infoseries;

import com.example.nefix.info.Info;
import com.example.nefix.series.Series;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class InfoSeries
{
    @EmbeddedId
    @JsonProperty("id")
    private InfoSeriesId id;

    @ManyToOne
    @JoinColumn(name = "info_id", insertable = false, updatable = false)
    @JsonProperty("infoId")
    private Info info;

    @ManyToOne
    @JoinColumn(name = "series_id", insertable = false, updatable = false)
    @JsonProperty("seriesId")
    private Series series;
}