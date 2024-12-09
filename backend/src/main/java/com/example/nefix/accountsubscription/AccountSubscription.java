package com.example.nefix.accountsubscription;

import com.example.nefix.account.Account;
import com.example.nefix.subscription.Subscription;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@IdClass(AccountSubscriptionId.class)
public class AccountSubscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscription_id")
    private Long subscriptionId;

    @ManyToOne
    @JoinColumn(name = "accountId", insertable = false, updatable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "subscriptionId", insertable = false, updatable = false)
    private Subscription subscription;

    @JsonProperty("dateOfPurchase")
    @Column(name = "date_of_purchase")
    private LocalDate dateOfPurchase;

    @JsonProperty("dateOfPurchase")
    @Column(name = "date_of_xxpire")
    private LocalDate dateOfExpire;
}
