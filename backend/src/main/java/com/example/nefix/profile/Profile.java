package com.example.nefix.profile;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Profile
{
    @Id
    private Long profileId;

    public void setProfileId(Long profileId)
    {
        this.profileId = profileId;
    }

    public Long getProfileId()
    {
        return profileId;
    }
}
