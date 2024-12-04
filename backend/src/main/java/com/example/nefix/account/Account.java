package com.example.nefix.account;

import com.example.nefix.accountsubscriptions.AccountSubscriptions;
import com.example.nefix.blockedaccounts.BlockedAccount;
import com.example.nefix.profile.Profile;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Account
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AccountSubscriptions> subscriptions;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Profile> profiles;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BlockedAccount> blockedAccounts;

    private String email;
    private String password;
    private PaymentMethod paymentMethod;
}
