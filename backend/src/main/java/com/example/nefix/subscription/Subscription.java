package com.example.nefix.subscription;

import com.example.nefix.accountsubscription.AccountSubscription;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Subscription
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscription_id")
    private Long subscriptionId;

    @JsonProperty("description")
    private String description;

    @JsonProperty("subscriptionPrice")
    @Column(name = "subscription_price")
    private double subscriptionPrice;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AccountSubscription> accounts;
}
