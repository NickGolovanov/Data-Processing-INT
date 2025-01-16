package com.example.nefix.accountsubscription;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class AccountSubscriptionRequestDto {
    @NotNull(message = "Date of purchase cannot be null")
    @PastOrPresent(message = "Date of purchase must be in the past or today")
    private LocalDate dateOfPurchase;

    @NotNull(message = "Date of expiration cannot be null")
    @Future(message = "Date of expiration must be in the future")
    private LocalDate dateOfExpire;
}
