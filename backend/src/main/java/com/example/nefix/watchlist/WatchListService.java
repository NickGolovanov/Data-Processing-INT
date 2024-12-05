package com.example.nefix.watchlist;

import com.example.nefix.genrealization.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class WatchListService extends BaseService<WatchList, Long> {
    public WatchListService(WatchListRepository repository) {
        super(repository);
    }
}
