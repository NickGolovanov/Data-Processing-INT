package com.example.nefix.infoseries;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class InfoSeriesId implements Serializable
{
    @Column(name = "info_id")
    private Long infoId;

    @Column(name = "series_id")
    private Long seriesId;

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        InfoSeriesId that = (InfoSeriesId) o;
        return Objects.equals(infoId, that.infoId) && Objects.equals(seriesId, that.seriesId);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(infoId, seriesId);
    }
}
