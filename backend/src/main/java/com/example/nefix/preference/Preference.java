package com.example.nefix.preference;

import com.example.nefix.profile.Profile;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Preference {
    @Id
    private Long preferenceId;

    @OneToOne
    @JoinColumn(name = "profileId", insertable = false, updatable = false)
    private Profile profile;
}
