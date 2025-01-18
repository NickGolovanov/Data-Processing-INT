package com.example.nefix.movie;

import com.example.nefix.genrealization.service.BaseService;
import com.example.nefix.info.Info;
import com.example.nefix.info.InfoRepository;
import com.example.nefix.infomovie.InfoMovie;
import com.example.nefix.infomovie.InfoMovieRepository;
import com.example.nefix.liveinfo.LiveInfo;
import com.example.nefix.liveinfo.LiveInfoRepository;
import com.example.nefix.subtitle.Subtitle;
import com.example.nefix.subtitle.SubtitleRepository;
import com.example.nefix.subtitle.SubtitleResponseDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService extends BaseService<Movie, Long>
{
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private SubtitleRepository subtitleRepository;
    @Autowired
    private InfoMovieRepository infoMovieRepository;
    @Autowired
    private InfoRepository infoRepository;

    public MovieService(MovieRepository repository)
    {
        super(repository, List.of("movieId"));
    }

    public List<MovieGeneralDto> getMovieGeneralDto()
    {
        return this.movieRepository.findAll().stream()
                .map(MovieGeneralDto::new)
                .toList();
    }

    public Subtitle addSubtitle(Long movieId, Subtitle subtitle)
    {
        long subtitleId = this.subtitleRepository.callAddSubtitleMovie(
                movieId,
                subtitle.getLanguage(),
                subtitle.getSubtitleLocation()
        );
        return this.subtitleRepository.findById(subtitleId).orElseThrow(() -> new RuntimeException("Subtitle not found"));
    }

    public List<SubtitleResponseDTO> getSubtitles(Long movieId)
    {
        return this.subtitleRepository.findAllByMovie_MovieId(movieId).stream()
                .map(SubtitleResponseDTO::new)
                .toList();
    }

    public Subtitle getSubtitle(Long movieId, Long subtitleId)
    {
        return this.subtitleRepository.findSubtitleBySubtitleIdAndMovie_MovieId(movieId, subtitleId);
    }

    @Transactional
    public Subtitle updateSubtitle(Long movieId, Long subtitleId, Subtitle updatedSubtitle)
    {
        this.subtitleRepository.callUpdateSubtitleMovie(
                subtitleId,
                movieId,
                updatedSubtitle.getLanguage(),
                updatedSubtitle.getSubtitleLocation()
        );

        return subtitleRepository.findById(subtitleId).orElseThrow(() -> new RuntimeException("Subtitle not found"));
    }

    public void deleteSubtitle(Long movieId, Long subtitleId)
    {
        Subtitle subtitle = this.getSubtitle(movieId, subtitleId);
        subtitleRepository.delete(subtitle);
    }

    public List<InfoMovie> getMovieInfoByMovieId(Long movieId)
    {
        return this.infoMovieRepository.findAllByMovie_MovieId(movieId);
    }

    @Transactional
    public Info addInfoMovie(Long movieId, Info info)
    {
        long infoId = this.infoMovieRepository.callAddInfoMovie(
                movieId,
                info.getDescription(),
                info.getType().toString()
        );
        return this.infoRepository.findById(infoId).orElseThrow(() -> new RuntimeException("Info not found"));
    }

    @Transactional
    public void deleteInfoMovie(Long movieId, Long infoId)
    {
        this.infoMovieRepository.callDeleteInfoMovie(movieId, infoId);
    }

    @Transactional
    public Info updateInfoMovie(Long movieId, Long infoId, Info updatedInfo)
    {
        this.infoMovieRepository.callUpdateInfoMovie(
                infoId,
                movieId,
                updatedInfo.getDescription(),
                updatedInfo.getType().toString()
        );

        return this.infoRepository.findById(infoId).orElseThrow(() -> new RuntimeException("InfoMovie not found"));
    }
}
