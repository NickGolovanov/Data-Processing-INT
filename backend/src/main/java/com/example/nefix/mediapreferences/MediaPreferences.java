package com.example.nefix.mediapreferences;

import com.example.nefix.blockedaccount.BlockedAccount;
import com.example.nefix.movie.Movie;
import com.example.nefix.preference.Preference;
import com.example.nefix.series.Series;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
@Entity
public class MediaPreferences implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "media_preference_id")
    private Long mediaPreferenceId;

    @ManyToOne
    @JoinColumn(name = "preference_id")
    @JsonProperty("preferenceId")
    private Preference preference;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    @JsonProperty("movieId")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "series_id", updatable = false, insertable = false)
    @JsonProperty("seriesId")
    private Series series;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MediaPreferences that = (MediaPreferences) o;
        return Objects.equals(this.mediaPreferenceId, that.mediaPreferenceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.mediaPreferenceId);
    }
}
