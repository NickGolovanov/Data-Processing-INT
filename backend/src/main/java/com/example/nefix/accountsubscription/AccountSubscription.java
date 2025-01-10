package com.example.nefix.accountsubscription;

import com.example.nefix.account.Account;
import com.example.nefix.subscription.Subscription;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account_subscription")
public class AccountSubscription
{
    @EmbeddedId
    @JsonProperty("id")
    private AccountSubscriptionId id;

    @ManyToOne
    @MapsId("accountId")
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    @JsonProperty("accountId")
    @JsonIgnoreProperties("subscriptions")
    private Account account;

    @ManyToOne
    @MapsId("subscriptionId")
    @JoinColumn(name = "subscription_id", insertable = false, updatable = false)
    @JsonProperty("subscriptionId")
    @JsonIgnoreProperties("accounts")
    private Subscription subscription;

    @JsonProperty("dateOfPurchase")
    @Column(name = "date_of_purchase")
    private LocalDate dateOfPurchase;

    @JsonProperty("dateOfExpire")
    @Column(name = "date_of_expire")
    private LocalDate dateOfExpire;
}
