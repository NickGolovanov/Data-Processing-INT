package com.example.nefix.accountsubscription;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class AccountSubscriptionRequestDto
{
    @NotNull(message = "Date of purchase cannot be empty")
    @FutureOrPresent(message = "Date of purchase cannot be in the past")
    private LocalDate dateOfPurchase;
    @NotNull(message = "Date of expire cannot be empty")
    @FutureOrPresent(message = "Date of expire cannot be in the past")
    private LocalDate dateOfExpire;
}
