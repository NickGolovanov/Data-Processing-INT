package com.example.nefix.blockedaccount;

import com.example.nefix.account.Account;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class BlockedAccount
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blocked_accountId")
    private Long blockedAccountId;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "accountId", nullable = false, insertable = false, updatable = false)
    @JsonProperty("accountId")
    private Account account;

    @JsonProperty("dateOfExpire")
    @Column(name = "date_of_expire")
    private LocalDate dateOfExpire;

    @JsonProperty("isPermanent")
    @Column(name = "is_permanent")
    private Boolean isPermanent;
}
