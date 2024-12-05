package com.example.nefix.info;

import com.example.nefix.genrealization.controller.BaseController;
import com.example.nefix.genrealization.service.ServiceBase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class InfoController extends BaseController<Info, Long>
{
    public InfoController(InfoService service)
    {
        super(service);
    }
}
