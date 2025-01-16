package com.example.nefix.profile;

import com.example.nefix.account.Account;
import com.example.nefix.account.AccountDeserializer;
import com.example.nefix.liveinfo.LiveInfo;
import com.example.nefix.preference.Preference;
import com.example.nefix.watchlist.WatchList;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
public class Profile implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long profileId;

    @JsonProperty("series")
    @ColumnDefault("true")
    private Boolean series;

    @JsonProperty("film")
    @ColumnDefault("true")
    private Boolean film;

    @Column(name = "minimum_age")
    @JsonProperty("minimumAge")
    @Min(value = 0, message = "Min age must not be negative.")
    private Integer minimumAge;

    @Column(name = "profile_image")
    @JsonProperty("profileImage")
    private String profileImage;

    @Column(name = "profile_child")
    @JsonProperty("profileChild")
    @NotNull(message = "Profile child must not be null.")
    private Boolean profileChild;

    @Column(name = "profile_name")
    @JsonProperty("profileName")
    @NotBlank(message = "Profile name must not be blank.")
    private String profileName;

    @JsonProperty("age")
    @Min(value = 0, message = "Age must not be negative.")
    private Integer age;

    @JsonProperty("language")
    @NotBlank(message = "Language must not be blank.")
    private String language;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    @JsonProperty(value = "accountId", access = JsonProperty.Access.WRITE_ONLY)
    @JsonDeserialize(using = AccountDeserializer.class)
    @NotNull(message = "Account must not be null.")
    private Account account;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonProperty("liveInfos")
    private Set<LiveInfo> liveInfos = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "preference_id", nullable = true)
    @JsonProperty(value = "preferenceId", access = JsonProperty.Access.READ_ONLY)
    private Preference preference;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"profileId"})
    private Set<WatchList> watchList = new HashSet<>();

    @JsonProperty(value = "accountId", access = JsonProperty.Access.READ_ONLY)
    public Long getAccountId() {
        return this.account.getAccountId();
    }

    @JsonProperty(value = "preferenceId", access = JsonProperty.Access.READ_ONLY)
    public Long getPreferenceId() {
        if (this.preference == null) {
            return null;
        }
        return this.preference.getPreferenceId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile that = (Profile) o;
        return Objects.equals(this.profileId, that.profileId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.profileId);
    }
}
