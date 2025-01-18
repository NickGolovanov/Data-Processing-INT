package com.example.nefix.movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class MovieGeneralDto
{
    private Long id;
    private String title;

    public MovieGeneralDto(Movie movie)
    {
        this.id = movie.getMovieId();
        this.title = movie.getTitle();
    }
}
