package com.example.nefix.preference;

import com.example.nefix.info.Info;
import com.example.nefix.profile.Profile;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@IdClass(PreferenceId.class)
public class Preference
{
    @Id
    private Long preferenceIdl;

    @OneToOne()
    @JoinColumn(name = "profileid", insertable = false, updatable = false)
    private Profile profile;
}
