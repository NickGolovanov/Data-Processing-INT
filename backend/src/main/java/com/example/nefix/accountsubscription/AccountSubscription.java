package com.example.nefix.accountsubscription;

import com.example.nefix.account.Account;
import com.example.nefix.subscription.Subscription;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class AccountSubscription
{
    @EmbeddedId
    @JsonProperty("id")
    private AccountSubscriptionId id;

    @ManyToOne
    @MapsId("accountId")
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    @JsonProperty("accountId")
    @JsonBackReference
    private Account account;

    @ManyToOne
    @MapsId("subscriptionId")
    @JoinColumn(name = "subscription_id", insertable = false, updatable = false)
    @JsonProperty("subscriptionId")
    private Subscription subscription;

    @JsonProperty("dateOfPurchase")
    @Column(name = "date_of_purchase")
    private LocalDate dateOfPurchase;

    @JsonProperty("dateOfExpire")
    @Column(name = "date_of_expire")
    private LocalDate dateOfExpire;
}
