package com.example.nefix.infoseries;

import com.example.nefix.blockedaccount.BlockedAccount;
import com.example.nefix.info.Info;
import com.example.nefix.series.Series;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
@Entity
public class InfoSeries implements Serializable
{
    @EmbeddedId
    @JsonProperty("id")
    private InfoSeriesId id;

    @ManyToOne
    @JoinColumn(name = "info_id", insertable = false, updatable = false)
    @JsonProperty(value = "infoId", access = JsonProperty.Access.WRITE_ONLY)
    private Info info;

    @ManyToOne
    @JoinColumn(name = "series_id", insertable = false, updatable = false)
    @JsonProperty(value = "seriesId", access = JsonProperty.Access.WRITE_ONLY)
    private Series series;

    @JsonProperty("infoId")
    public Long getInfoId()
    {
        return this.info.getInfoId();
    }

    @JsonProperty("seriesId")
    public Long getSeriesId()
    {
        return this.series.getSeriesId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfoSeries that = (InfoSeries) o;
        return Objects.equals(this.id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}