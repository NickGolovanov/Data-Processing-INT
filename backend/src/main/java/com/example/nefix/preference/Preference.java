package com.example.nefix.preference;

import com.example.nefix.account.AccountDeserializer;
import com.example.nefix.profile.Profile;
import com.example.nefix.profile.ProfileDeserializer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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
    @JoinColumn(name = "profile_id", nullable = false)
    @JsonProperty("profileId")
    @JsonDeserialize(using = ProfileDeserializer.class)
    @JsonIgnoreProperties({"preference", "liveInfos", "watchList"})
    private Profile profile;
}
