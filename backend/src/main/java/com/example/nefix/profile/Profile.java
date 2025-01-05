package com.example.nefix.profile;

import com.example.nefix.account.Account;
import com.example.nefix.account.AccountDeserializer;
import com.example.nefix.liveinfo.LiveInfo;
import com.example.nefix.preference.Preference;
import com.example.nefix.watchlist.WatchList;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Profile
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long profileId;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    @JsonProperty("accountId")
    @JsonDeserialize(using = AccountDeserializer.class)
    @JsonIgnoreProperties({"profiles", "blockedAccounts", "referralDiscount", "subscriptions"})
    @NotNull(message = "Account must not be null.")
    private Account account;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"profile", "episode", "movie"})
    private Set<LiveInfo> liveInfos = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "preference_id", nullable = true)
    @JsonProperty("preferenceId")
    @JsonIgnoreProperties({"profile"})
    private Preference preference;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"profile", "series", "movie"})
    private Set<WatchList> watchList = new HashSet<>();

    @JsonProperty("series")
    @ColumnDefault("true")
    private Boolean series;

    @JsonProperty("film")
    @ColumnDefault("true")
    private Boolean film;

    @JsonProperty("minimumAge")
    @Column(name = "minimum_age")
    @Min(value = 0, message = "Min age must not be negative.")
    private Integer minimumAge;

    @JsonProperty("profileImage")
    @Column(name = "profile_image")
    @NotBlank(message = "Profile image must not be blank.")
    private String profileImage;

    @JsonProperty("profileChild")
    @Column(name = "profile_child")
    private Boolean profileChild;

    @JsonProperty("profileName")
    @Column(name = "profile_name")
    @NotBlank(message = "Profile name must not be blank.")
    private String profileName;

    @JsonProperty("age")
    @Min(value = 0, message = "Age must not be negative.")
    private Integer age;

    @JsonProperty("language")
    @NotBlank(message = "Language must not be blank.")
    private String language;
}
