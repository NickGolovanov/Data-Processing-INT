package com.example.nefix.preference;

import com.example.nefix.genrealization.service.BaseService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PreferenceService extends BaseService<Preference, Long>
{
    public PreferenceService(PreferenceRepository repository)
    {
        super(repository);
    }
}
