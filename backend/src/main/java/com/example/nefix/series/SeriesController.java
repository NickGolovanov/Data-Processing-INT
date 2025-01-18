package com.example.nefix.series;

import com.example.nefix.genrealization.controller.BaseController;
import com.example.nefix.genrealization.response.ApiResponse;
import com.example.nefix.genrealization.response.ErrorResponse;
import com.example.nefix.info.Info;
import com.example.nefix.infomovie.InfoMovie;
import com.example.nefix.infoseries.InfoSeries;
import com.example.nefix.season.Season;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/series")
public class SeriesController extends BaseController<Series, Long>
{
    @Autowired
    private SeriesService seriesService;

    public SeriesController(SeriesService service)
    {
        super(service);
    }

    @GetMapping("/general")
    public ResponseEntity<ApiResponse<List<SeriesGeneralDto>>> getMovieGeneralDto()
    {
        try
        {
            List<SeriesGeneralDto> seriesGeneralDto = this.seriesService.getSeriesGeneralDto();

            return ResponseEntity.ok(new ApiResponse<>(seriesGeneralDto, null));
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(null, new ErrorResponse(e.getMessage())));
        }
    }

    @GetMapping("/{seriesId}/info-series")
    public ResponseEntity<ApiResponse<List<InfoSeries>>> getInfoMoviesByMovieId(@PathVariable Long seriesId)
    {
        try
        {
            List<InfoSeries> infoSeries = this.seriesService.getSeriesInfoBySeriesId(seriesId);

            if (infoSeries.isEmpty())
            {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(new ApiResponse<>(infoSeries, null));
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(null, new ErrorResponse(e.getMessage())));
        }
    }

    @PostMapping("/{seriesId}/info-series")
    public ResponseEntity<ApiResponse<Info>> addInfoMovie(@PathVariable Long seriesId, @Valid @RequestBody Info info)
    {
        try
        {
            Info createdInfo = this.seriesService.addInfoSeries(seriesId, info);

            return ResponseEntity.ok(new ApiResponse<>(createdInfo, null));
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(null, new ErrorResponse(e.getMessage())));
        }
    }

    @DeleteMapping("/{seriesId}/info-series/{infoId}")
    public ResponseEntity<ApiResponse<Void>> deleteInfoMovie(@PathVariable Long seriesId, @PathVariable Long infoId)
    {
        try
        {
            this.seriesService.deleteInfoSeries(seriesId, infoId);

            return ResponseEntity.noContent().build();
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(null, new ErrorResponse(e.getMessage())));
        }
    }

    @PatchMapping("/{seriesId}/info-series/{infoId}")
    public ResponseEntity<ApiResponse<Info>> updateInfoMovie(@PathVariable Long seriesId, @PathVariable Long infoId, @Valid @RequestBody Info info)
    {
        try
        {
            Info updatedInfo = this.seriesService.updateInfoSeries(seriesId, infoId, info);

            return ResponseEntity.ok(new ApiResponse<>(updatedInfo, null));
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(null, new ErrorResponse(e.getMessage())));
        }
    }
}
