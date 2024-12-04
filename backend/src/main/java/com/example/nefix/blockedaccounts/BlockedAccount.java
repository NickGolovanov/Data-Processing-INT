package com.example.nefix.blockedaccounts;

import com.example.nefix.account.Account;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class BlockedAccount
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blockedAccountId;

    @ManyToOne
    @JoinColumn(name = "accountId", insertable = false, updatable = false)
    private Account account;

    private LocalDate dateOfExpire;
    private Boolean isPermanent;
}
