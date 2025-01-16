package com.example.nefix.info;

import com.example.nefix.genrealization.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoService extends BaseService<Info, Long>
{
    public InfoService(InfoRepository repository)
    {
        super(repository, List.of("infoId"));
    }
}
