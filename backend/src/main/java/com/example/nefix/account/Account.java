package com.example.nefix.account;

import com.example.nefix.accountsubscription.AccountSubscription;
import com.example.nefix.blockedaccount.BlockedAccount;
import com.example.nefix.profile.Profile;
import com.example.nefix.referraldiscount.ReferralDiscount;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AccountSubscription> subscriptions;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Profile> profiles;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BlockedAccount> blockedAccounts;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "referral_discount_id")
    @JsonProperty("referralDiscount")
    private ReferralDiscount referralDiscount;

    @Enumerated(EnumType.STRING)
    @JsonProperty("paymentMethod")
    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;

    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;
}