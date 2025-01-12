package com.example.nefix.series;

import com.example.nefix.movie.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class SeriesGeneralDto {
        private Long id;
        private String title;

        public SeriesGeneralDto(Series series) {
                this.id = series.getSeriesId();
                this.title = series.getTitle();
        }

}
