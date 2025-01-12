package com.example.nefix.subscription;

import com.example.nefix.accountsubscription.AccountSubscription;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
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
    private Double subscriptionPrice;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonIgnoreProperties({"subscription", "accounts"})
    @JsonManagedReference
    private Set<AccountSubscription> accounts = new HashSet<>();
}
