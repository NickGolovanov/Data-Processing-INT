package com.example.nefix.preference;

import com.example.nefix.profile.Profile;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Preference
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "preference_id")
    private Long preferenceId;

    @OneToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "profileId", insertable = false, updatable = false)
    @JsonProperty("profileId")
    private Profile profile;
}
