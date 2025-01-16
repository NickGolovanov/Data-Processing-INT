package com.example.nefix.preference;

import com.example.nefix.account.AccountDeserializer;
import com.example.nefix.blockedaccount.BlockedAccount;
import com.example.nefix.mediapreferences.MediaPreferences;
import com.example.nefix.profile.Profile;
import com.example.nefix.profile.ProfileDeserializer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
public class Preference implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "preference_id")
    private Long preferenceId;

    @OneToOne
    @JoinColumn(name = "profile_id", nullable = false)
    @JsonProperty(value = "profileId", access = JsonProperty.Access.WRITE_ONLY)
    @JsonDeserialize(using = ProfileDeserializer.class)
    private Profile profile;

    @JsonProperty(value = "profileId", access = JsonProperty.Access.READ_ONLY)
    private Long profileId(){
        return profile.getProfileId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Preference that = (Preference) o;
        return Objects.equals(this.preferenceId, that.preferenceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.preferenceId);
    }
}
