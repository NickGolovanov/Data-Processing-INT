package com.example.nefix.blockedaccount;

import com.example.nefix.account.Account;
import com.example.nefix.account.AccountDeserializer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "blocked_account")
public class BlockedAccount
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blocked_accountId")
    private Long blockedAccountId;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    @JsonProperty("accountId")
    @JsonDeserialize(using = AccountDeserializer.class)
    @JsonIgnoreProperties({"blockedAccounts", "referralDiscount", "subscriptions", "profiles"})
    private Account account;

    @JsonProperty("dateOfExpire")
    @Column(name = "date_of_expire")
    private LocalDate dateOfExpire;

    @JsonProperty("isPermanent")
    @Column(name = "is_permanent")
    private Boolean isPermanent;
}
