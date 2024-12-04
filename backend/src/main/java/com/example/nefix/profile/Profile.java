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
public class Profile
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;

    @ManyToOne()
    @JoinColumn(name = "accountId")
    private Account account;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "liveInfo")
    private Set<LiveInfo> infoMovies;

    @OneToOne(mappedBy = "preference")
    private Preference preference;

    @OneToMany(mappedBy = "profileId")
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
