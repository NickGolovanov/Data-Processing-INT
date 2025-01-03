package com.example.nefix.account;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class AccountSubscriptionRequestDto {
    private LocalDate dateOfPurchase;
    private LocalDate dateOfExpire;
}
