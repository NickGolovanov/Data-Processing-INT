package com.example.nefix.preference;

import com.example.nefix.genrealization.controller.BaseController;
import com.example.nefix.genrealization.service.ServiceBase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/preference")
public class PreferenceController extends BaseController<Preference, Long>
{
    public PreferenceController(PreferenceService service)
    {
        super(service);
    }
}
