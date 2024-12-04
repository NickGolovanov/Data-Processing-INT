package com.example.nefix.infomovie;

import com.example.nefix.accountsubscriptions.AccountSubscriptionId;
import com.example.nefix.info.Info;
import com.example.nefix.movie.Movie;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@IdClass(InfoMovieId.class)
public class InfoMovie
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long infoId;

    @ManyToOne
    @JoinColumn(name = "movieId", insertable = false,updatable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "infoId", insertable = false,updatable = false)
    private Info info;
}
