package com.example.nefix.profile;

import com.example.nefix.account.Account;
import com.example.nefix.liveinfo.LiveInfo;
import com.example.nefix.preference.Preference;
import com.example.nefix.watchlist.WatchList;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;

    @ManyToOne
    // name is the name of column in db, referencedColumnName is the name of column in hibernate
    @JoinColumn(name = "account_id", referencedColumnName = "accountId", nullable = false)
    private Account account;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<LiveInfo> liveInfos;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    // name is the name of column in db, referencedColumnName is the name of column in hibernate
    @JoinColumn(name = "preference_id", referencedColumnName = "preferenceId", nullable = false)
    private Preference preference;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<WatchList> watchList;

    private Boolean series;
    private Boolean film;
    private Integer minimumAge;
    private String profileImage;
    private Boolean profileChild;
    private String profileName;
    private Integer age;
    private String language;
}
