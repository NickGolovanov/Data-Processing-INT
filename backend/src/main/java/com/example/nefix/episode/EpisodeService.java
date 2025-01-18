package com.example.nefix.episode;

import com.example.nefix.genrealization.service.BaseService;
import com.example.nefix.subtitle.Subtitle;
import com.example.nefix.subtitle.SubtitleRepository;
import com.example.nefix.subtitle.SubtitleResponseDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpisodeService extends BaseService<Episode, Long>
{
    @Autowired
    private SubtitleRepository subtitleRepository;

    public EpisodeService(EpisodeRepository repository)
    {
        super(repository, List.of("episodeId"));
    }

    public Subtitle addSubtitle(Long episodeId, Subtitle subtitle)
    {
        long subtitleId = subtitleRepository.callAddSubtitleEpisode(
                episodeId,
                subtitle.getLanguage(),
                subtitle.getSubtitleLocation()
        );
        return subtitleRepository.findById(subtitleId).orElseThrow(() -> new RuntimeException("Subtitle not found"));
    }

    public List<SubtitleResponseDTO> getSubtitles(Long episodeId)
    {
        return this.subtitleRepository.findAllByEpisode_EpisodeId(episodeId).stream()
                .map(SubtitleResponseDTO::new)
                .toList();
    }

    public Subtitle getSubtitle(Long movieId, Long subtitleId)
    {
        return subtitleRepository.findSubtitleBySubtitleIdAndEpisode_EpisodeId(movieId, subtitleId);
    }

    @Transactional
    public Subtitle updateSubtitle(Long movieId, Long subtitleId, Subtitle updatedSubtitle)
    {
        subtitleRepository.callUpdateSubtitleEpisode(
                subtitleId,
                movieId,
                updatedSubtitle.getLanguage(),
                updatedSubtitle.getSubtitleLocation()
        );

        return subtitleRepository.findById(subtitleId).orElseThrow(() -> new RuntimeException("Subtitle not found"));
    }

    public void deleteSubtitle(Long episodeId, Long subtitleId)
    {
        Subtitle subtitle = getSubtitle(episodeId, subtitleId);
        subtitleRepository.delete(subtitle);
    }
}
