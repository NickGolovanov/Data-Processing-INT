package com.example.nefix.profile;

import com.example.nefix.account.Account;
import com.example.nefix.account.AccountDeserializer;
import com.example.nefix.liveinfo.LiveInfo;
import com.example.nefix.preference.Preference;
import com.example.nefix.watchlist.WatchList;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Data;

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
    private Account account;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"profile", "episode", "movie"})
    private Set<LiveInfo> liveInfos = new HashSet<>();

//    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "preference_id", nullable = true)
//    @JsonProperty("preferenceId")
//    @JsonIgnoreProperties({"profile"})
//    private Preference preference;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"profile", "series", "movie"})
    private Set<WatchList> watchList = new HashSet<>();

    @JsonProperty("series")
    private Boolean series;

    @JsonProperty("film")
    private Boolean film;

    @JsonProperty("minimumAge")
    @Column(name = "minimum_age")
    private Integer minimumAge;

    @JsonProperty("profileImage")
    @Column(name = "profile_image")
    private String profileImage;

    @JsonProperty("profileChild")
    @Column(name = "profile_child")
    private Boolean profileChild;

    @JsonProperty("profileName")
    @Column(name = "profile_name")
    private String profileName;

    @JsonProperty("age")
    private Integer age;

    @JsonProperty("language")
    private String language;
}
