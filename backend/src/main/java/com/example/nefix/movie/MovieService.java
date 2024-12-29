package com.example.nefix.movie;

import com.example.nefix.genrealization.service.BaseService;
import com.example.nefix.subtitle.Subtitle;
import com.example.nefix.subtitle.SubtitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService extends BaseService<Movie, Long> {
    @Autowired
    private SubtitleRepository subtitleRepository;


    public MovieService(MovieRepository repository) {
        super(repository);
    }


    public Subtitle addSubtitle(Long movieId, Subtitle subtitle) {
        Movie movie = repository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found with ID: " + movieId));
        subtitle.setMovie(movie);
        return subtitleRepository.save(subtitle);
    }

    public List<MovieSubtitleResponseDto> getSubtitles(Long movieId) {
        return subtitleRepository.findAll().stream()
                .filter(subtitle -> subtitle.getMovie().getMovieId().equals(movieId))
                .map(MovieSubtitleResponseDto::new)
                .toList();
    }

    public Subtitle getSubtitle(Long movieId, Long subtitleId) {
        return subtitleRepository.findById(subtitleId)
                .filter(subtitle -> subtitle.getMovie().getMovieId().equals(movieId))
                .orElseThrow(() -> new RuntimeException("Subtitle not found for Movie ID: " + movieId));
    }

    public Subtitle updateSubtitle(Long movieId, Long subtitleId, Subtitle updatedSubtitle) {
        Subtitle existingSubtitle = getSubtitle(movieId, subtitleId);

        existingSubtitle.setLanguage(updatedSubtitle.getLanguage());
        existingSubtitle.setSubtitleLocation(updatedSubtitle.getSubtitleLocation());

        return subtitleRepository.save(existingSubtitle);
    }

    public void deleteSubtitle(Long movieId, Long subtitleId) {
        Subtitle subtitle = getSubtitle(movieId, subtitleId);
        subtitleRepository.delete(subtitle);
    }

}

