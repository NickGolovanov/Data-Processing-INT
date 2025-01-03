package com.example.nefix.movie;

import com.example.nefix.genrealization.service.BaseService;
import com.example.nefix.info.Info;
import com.example.nefix.info.InfoRepository;
import com.example.nefix.infomovie.InfoMovie;
import com.example.nefix.infomovie.InfoMovieId;
import com.example.nefix.infomovie.InfoMovieRepository;
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
    private SubtitleRepository subtitleRepository;
    @Autowired
    private InfoMovieRepository infoMovieRepository;
    @Autowired
    private InfoRepository infoRepository;


    public MovieService(MovieRepository repository)
    {
        super(repository);
    }


    public Subtitle addSubtitle(Long movieId, Subtitle subtitle)
    {
        Movie movie = repository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found with ID: " + movieId));
        subtitle.setMovie(movie);
        return subtitleRepository.save(subtitle);
    }

    public List<SubtitleResponseDTO> getSubtitles(Long movieId)
    {
        return subtitleRepository.findAllByMovie_MovieId(movieId).stream()
                .map(SubtitleResponseDTO::new)
                .toList();
    }

    public Subtitle getSubtitle(Long movieId, Long subtitleId)
    {
        return subtitleRepository.findById(subtitleId)
                .filter(subtitle -> subtitle.getMovie().getMovieId().equals(movieId))
                .orElseThrow(() -> new RuntimeException("Subtitle not found for Movie ID: " + movieId));
    }

    //Example of using update with stored procedures
    @Transactional
    public Subtitle updateSubtitle(Long movieId, Long subtitleId, Subtitle updatedSubtitle)
    {
        System.out.println("movieId = " + movieId);
        subtitleRepository.callUpdateSubtitle(
                subtitleId,
                movieId,
                updatedSubtitle.getLanguage(),
                updatedSubtitle.getSubtitleLocation()
        );

        return getSubtitle(movieId, subtitleId);
    }

    public void deleteSubtitle(Long movieId, Long subtitleId)
    {
        Subtitle subtitle = getSubtitle(movieId, subtitleId);
        subtitleRepository.delete(subtitle);
    }

    public List<Subtitle> getMovieWithSubtitles(Long movieId)
    {
        try
        {
            return subtitleRepository.findAllByMovie_MovieId(movieId);
        } catch (Exception e)
        {
            throw new RuntimeException("Error while fetching movie with subtitles", e);
        }
    }

    public List<InfoMovie> getAllMovieInfo(Long movieId)
    {
        return infoMovieRepository.findAll();
    }

    public List<InfoMovie> getMovieInfoByMovieId(Long movieId)
    {
        return infoMovieRepository.findAllByMovie_MovieId(movieId);
    }

    public InfoMovie addInfoMovie(Long movieId, Info info)
    {
        Movie movie = repository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found with ID: " + movieId));

        infoRepository.save(info);

        InfoMovie infoMovie = new InfoMovie(
                new InfoMovieId(info.getInfoId(), movieId),
                movie,
                info
        );

        return infoMovieRepository.save(infoMovie);
    }

    public void deleteInfoMovie(Long movieId, Long infoId)
    {
        InfoMovie infoMovie = infoMovieRepository.findByMovie_MovieIdAndInfo_InfoId(movieId, infoId)
                .orElseThrow(() -> new RuntimeException("InfoMovie not found"));
        infoMovieRepository.delete(infoMovie);
    }

    @Transactional
    public InfoMovie updateInfoMovie(Long movieId, Long infoId, Info updatedInfo)
    {
        InfoMovie infoMovie = infoMovieRepository.findByMovie_MovieIdAndInfo_InfoId(movieId, infoId)
                .orElseThrow(() -> new RuntimeException("InfoMovie not found"));

        Info info = infoMovie.getInfo();

        info.setDescription(updatedInfo.getDescription());
        info.setType(updatedInfo.getType());

        infoRepository.save(info);

        return infoMovie;
    }
}
