package com.example.nefix.accountsubscription;

import com.example.nefix.account.Account;
import com.example.nefix.subscription.Subscription;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account_subscription")
public class AccountSubscription implements Serializable
{
    @EmbeddedId
    @JsonIgnore
    private AccountSubscriptionId id;

    @ManyToOne
    @MapsId("accountId")
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    @JsonIgnore
    private Account account;

    @ManyToOne
    @MapsId("subscriptionId")
    @JoinColumn(name = "subscription_id", insertable = false, updatable = false)
    @JsonIgnore
    private Subscription subscription;

    @Column(name = "date_of_purchase")
    @JsonProperty("dateOfPurchase")
    private LocalDate dateOfPurchase;

    @Column(name = "date_of_expire")
    @JsonProperty("dateOfExpire")
    private LocalDate dateOfExpire;

    @JsonProperty("accountId")
    public Long getAccountId()
    {
        return this.id.getAccountId();
    }

    @JsonProperty("subscriptionId")
    public Long getSubscriptionId()
    {
        return this.id.getSubscriptionId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountSubscription that = (AccountSubscription) o;
        return Objects.equals(this.id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
