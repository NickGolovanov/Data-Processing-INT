package com.example.nefix.profile;

import com.example.nefix.genrealization.service.BaseService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileService extends BaseService<Profile, Long>
{
    public ProfileService(ProfileRepository repository)
    {
        super(repository);
    }
}
