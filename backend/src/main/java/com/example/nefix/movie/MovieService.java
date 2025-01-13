package com.example.nefix.movie;

import com.example.nefix.genrealization.service.BaseService;
import com.example.nefix.info.Info;
import com.example.nefix.info.InfoRepository;
import com.example.nefix.infomovie.InfoMovie;
import com.example.nefix.infomovie.InfoMovieRepository;
import com.example.nefix.subtitle.Subtitle;
import com.example.nefix.subtitle.SubtitleRepository;
import com.example.nefix.subtitle.SubtitleResponseDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService extends BaseService<Movie, Long> {
    @Autowired
    private SubtitleRepository subtitleRepository;
    @Autowired
    private InfoMovieRepository infoMovieRepository;
    @Autowired
    private InfoRepository infoRepository;


    public MovieService(MovieRepository repository) {
        super(repository);
    }

    public List<MovieGeneralDto> getMovieGeneralDto() {
        return repository.findAll().stream()
                .map(MovieGeneralDto::new)
                .toList();
    }

    public Subtitle addSubtitle(Long movieId, Subtitle subtitle) {
        long subtitleId = subtitleRepository.callAddSubtitle(
                movieId,
                subtitle.getLanguage(),
                subtitle.getSubtitleLocation()
        );
        return subtitleRepository.findById(subtitleId).orElseThrow(() -> new RuntimeException("Subtitle not found"));
    }

    public List<SubtitleResponseDTO> getSubtitles(Long movieId) {
        return subtitleRepository.findAllByMovie_MovieId(movieId).stream()
                .map(SubtitleResponseDTO::new)
                .toList();
    }

    public Subtitle getSubtitle(Long movieId, Long subtitleId) {
        return subtitleRepository.findBySubtitleId_MovieId(movieId, subtitleId);
    }

    @Transactional
    public Subtitle updateSubtitle(Long movieId, Long subtitleId, Subtitle updatedSubtitle) {
        subtitleRepository.callUpdateSubtitle(
                subtitleId,
                movieId,
                updatedSubtitle.getLanguage(),
                updatedSubtitle.getSubtitleLocation()
        );

        return subtitleRepository.findById(subtitleId).orElseThrow(() -> new RuntimeException("Subtitle not found"));
    }

    public void deleteSubtitle(Long movieId, Long subtitleId) {
        Subtitle subtitle = getSubtitle(movieId, subtitleId);
        subtitleRepository.delete(subtitle);
    }

    public List<Subtitle> getMovieWithSubtitles(Long movieId) {
        return subtitleRepository.findAllByMovie_MovieId(movieId);
    }

    public List<InfoMovie> getAllMovieInfo(Long movieId) {
        return infoMovieRepository.findAll();
    }

    public List<InfoMovie> getMovieInfoByMovieId(Long movieId) {
        return infoMovieRepository.findAllByMovie_MovieId(movieId);
    }

    @Transactional
    public Info addInfoMovie(Long movieId, Info info) {
        long infoId = infoMovieRepository.callAddInfoMovie(
                movieId,
                info.getDescription(),
                info.getType().toString()
        );
        return infoRepository.findById(infoId).orElseThrow(() -> new RuntimeException("Info not found"));
    }

    public void deleteInfoMovie(Long movieId, Long infoId) {
//        InfoMovie infoMovie = infoMovieRepository.findByMovie_MovieIdAndInfo_InfoId(movieId, infoId)
//                .orElseThrow(() -> new RuntimeException("InfoMovie not found"));
//        infoMovieRepository.delete(infoMovie);
//        Info info = infoRepository.findById(infoId).orElseThrow(() -> new RuntimeException("Info wast not found in database"));
//        infoRepository.delete(info);
        infoMovieRepository.callDeleteInfoMovie(movieId, infoId);
    }

    @Transactional
    public Info updateInfoMovie(Long movieId, Long infoId, Info updatedInfo) {
        infoMovieRepository.callUpdateInfoMovie(
                infoId,
                movieId,
                updatedInfo.getDescription(),
                updatedInfo.getType().toString()
        );

        return infoRepository.findById(infoId).orElseThrow(() -> new RuntimeException("InfoMovie not found"));
    }
}
