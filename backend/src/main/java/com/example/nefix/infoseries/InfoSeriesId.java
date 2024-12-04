package com.example.nefix.infoseries;

import com.example.nefix.infomovie.InfoMovieId;

import java.io.Serializable;
import java.util.Objects;

public class InfoSeriesId implements Serializable
{
    private Long infoId;
    private Long seriesId;

    public InfoSeriesId() {}

    public InfoSeriesId(Long infoId, Long seriesId)
    {
        this.infoId = infoId;
        this.seriesId = seriesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfoSeriesId that = (InfoSeriesId) o;
        return Objects.equals(infoId, that.infoId) && Objects.equals(seriesId, that.seriesId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(infoId, seriesId);
    }
}
