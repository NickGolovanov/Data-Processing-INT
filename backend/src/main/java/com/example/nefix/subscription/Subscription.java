package com.example.nefix.subscription;
import com.example.nefix.account.Account;
import com.example.nefix.accountsubscriptions.AccountSubscriptions;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Subscription
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subscriptionid;

    private String description;

    private double subscriptionPrice;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AccountSubscriptions> accounts;
}
