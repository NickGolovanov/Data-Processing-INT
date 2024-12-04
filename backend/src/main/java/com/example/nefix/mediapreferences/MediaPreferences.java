package com.example.nefix.mediapreferences;

import com.example.nefix.movie.Movie;
import com.example.nefix.preference.Preference;
import com.example.nefix.series.Series;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class MediaPreferences
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mediaPreferenceId;

    @ManyToOne
    @JoinColumn(name = "preferenceId", updatable = false, insertable = false)
    private Preference preference;

    @ManyToOne
    @JoinColumn(name = "movieId", updatable = false, insertable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "seriesId", updatable = false, insertable = false)
    private Series series;
}
