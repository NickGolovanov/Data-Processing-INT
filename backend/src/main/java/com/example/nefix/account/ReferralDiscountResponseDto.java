package com.example.nefix.account;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReferralDiscountResponseDto {
    private Long referralDiscountId;
    private String link; // Referral link/code
}
