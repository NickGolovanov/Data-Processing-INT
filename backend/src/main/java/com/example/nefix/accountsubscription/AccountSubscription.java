package com.example.nefix.accountsubscription;

import com.example.nefix.account.Account;
import com.example.nefix.subscription.Subscription;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@IdClass(AccountSubscriptionId.class)
public class AccountSubscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subscriptionId;

    @ManyToOne
    @JoinColumn(name = "accountId", insertable = false, updatable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "subscriptionId", insertable = false, updatable = false)
    private Subscription subscription;

    private LocalDate dateOfPurchase;

    private LocalDate dateOfExpire;

}
