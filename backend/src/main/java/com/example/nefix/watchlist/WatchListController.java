package com.example.nefix.watchlist;

import com.example.nefix.genrealization.controller.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/watch-list")
public class WatchListController extends BaseController<WatchList, Long> {
    public WatchListController(WatchListService service) {
        super(service);
    }


}
