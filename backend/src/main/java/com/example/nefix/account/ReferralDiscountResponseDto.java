package com.example.nefix.account;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReferralDiscountResponseDto {
    @NotNull(message = "Referral discount ID cannot be null")
    private Long referralDiscountId;
    @NotNull(message = "Link cannot be null")
    private String link;
}
