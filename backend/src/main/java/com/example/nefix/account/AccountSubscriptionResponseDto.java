package com.example.nefix.account;

import com.example.nefix.accountsubscription.AccountSubscription;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class AccountSubscriptionResponseDto {
    private Long accountId;
    private Long subscriptionId;
    private LocalDate dateOfPurchase;
    private LocalDate dateOfExpire;
}
