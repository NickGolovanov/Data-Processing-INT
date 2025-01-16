package com.example.nefix.subscription;

import com.example.nefix.accountsubscription.AccountSubscription;
import com.example.nefix.blockedaccount.BlockedAccount;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
public class Subscription implements Serializable
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
    @JsonIgnore
    private Set<AccountSubscription> accounts = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscription that = (Subscription) o;
        return Objects.equals(this.subscriptionId, that.subscriptionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.subscriptionId);
    }
}
