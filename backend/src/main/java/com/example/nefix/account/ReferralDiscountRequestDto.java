package com.example.nefix.account;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReferralDiscountRequestDto {
    @NotNull(message = "Link cannot be null")
    private String link; // Referral link or code
}
