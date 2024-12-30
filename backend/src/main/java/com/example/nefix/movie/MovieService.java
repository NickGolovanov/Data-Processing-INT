package com.example.nefix.movie;

import com.example.nefix.genrealization.service.BaseService;
import com.example.nefix.subtitle.Subtitle;
import com.example.nefix.subtitle.SubtitleRepository;
import com.example.nefix.subtitle.SubtitleViewDTO;
import jakarta.transaction.Transactional;
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

    //Example of using update with stored procedures
    @Transactional
    public Subtitle updateSubtitle(Long movieId, Long subtitleId, Subtitle updatedSubtitle) {
        System.out.println("movieId = " + movieId);
        subtitleRepository.callUpdateSubtitle(
                subtitleId,
                movieId,
                updatedSubtitle.getLanguage(),
                updatedSubtitle.getSubtitleLocation()
        );

        return getSubtitle(movieId, subtitleId);
    }

    public void deleteSubtitle(Long movieId, Long subtitleId) {
        Subtitle subtitle = getSubtitle(movieId, subtitleId);
        subtitleRepository.delete(subtitle);
    }

    public List<SubtitleViewDTO> getMovieWithSubtitles() {
        try {
            List<Object[]> results = ((MovieRepository) repository).findMovieWithSubtitles();
            return viewResultToListSubtitleViewDto(results);
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching movie with subtitles", e);
        }
    }


    public List<SubtitleViewDTO> viewResultToListSubtitleViewDto(List<Object[]> results) {
        return results.stream()
                .map(result -> new SubtitleViewDTO(
                        ((Number) result[0]).longValue(),   // subtitleId
                        (String) result[1],                // language
                        (String) result[2],                // subtitleLocation
                        result[3] != null ? ((Number) result[3]).longValue() : null,  // episodeId
                        (String) result[4],                // episodeTitle
                        ((Number) result[5]).longValue(),  // movieId
                        (String) result[6]                 // movieTitle
                ))
                .toList();
    }
}

