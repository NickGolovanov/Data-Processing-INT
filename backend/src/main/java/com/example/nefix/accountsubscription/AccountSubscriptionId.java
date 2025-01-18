package com.example.nefix.accountsubscription;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
public class AccountSubscriptionId implements Serializable
{
    private Long accountId;

    private Long subscriptionId;

    public AccountSubscriptionId()
    {
    }

    public AccountSubscriptionId(Long accountId, Long subscriptionId)
    {
        this.accountId = accountId;
        this.subscriptionId = subscriptionId;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AccountSubscriptionId that = (AccountSubscriptionId) o;
        return Objects.equals(accountId, that.accountId) && Objects.equals(subscriptionId, that.subscriptionId);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(accountId, subscriptionId);
    }
}
