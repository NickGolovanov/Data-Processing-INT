package com.example.nefix.accountsubscriptions;
import lombok.Data;
import java.io.Serializable;

@Data
public class AccountSubscriptionId implements Serializable
{
    private Long accountId;

    private Long subscriptionId;
}
