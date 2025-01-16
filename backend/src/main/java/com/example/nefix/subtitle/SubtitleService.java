package com.example.nefix.subtitle;

import com.example.nefix.genrealization.service.BaseService;
import com.example.nefix.referraldiscount.ReferralDiscount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubtitleService extends BaseService<Subtitle, Long> {
    public SubtitleService(SubtitleRepository repository) {
        super(repository, List.of("subtitleId"));
    }
}
