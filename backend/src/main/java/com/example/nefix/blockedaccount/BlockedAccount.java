package com.example.nefix.blockedaccount;

import com.example.nefix.account.Account;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class BlockedAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blockedAccountId;

    @ManyToOne
    // name is the name of column in db, referencedColumnName is the name of column in hibernate
    @JoinColumn(name = "account_id", referencedColumnName = "accountId", nullable = false, insertable = false, updatable = false)
    private Account account;

    private LocalDate dateOfExpire;
    private Boolean isPermanent;
}
