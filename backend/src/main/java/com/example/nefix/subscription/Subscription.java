package com.example.nefix.subscription;

import com.example.nefix.accountsubscription.AccountSubscription;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subscriptionId;

    private String description;

    private double subscriptionPrice;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AccountSubscription> accounts;
}
