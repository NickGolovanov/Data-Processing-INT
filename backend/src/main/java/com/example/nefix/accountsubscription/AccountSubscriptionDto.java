package com.example.nefix.accountsubscription;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class AccountSubscriptionDto
{
    @NotNull(message = "Account ID cannot be null")
    private final Long accountId;

    @NotNull(message = "Subscription ID cannot be null")
    private final Long subscriptionId;

    @NotNull(message = "Date of purchase cannot be null")
    @PastOrPresent(message = "Date of purchase must be in the past or present")
    private final LocalDate dateOfPurchase;

    @NotNull(message = "Date of expiration cannot be null")
    @Future(message = "Date of expiration must be in the future")
    private final LocalDate dateOfExpire;
}
