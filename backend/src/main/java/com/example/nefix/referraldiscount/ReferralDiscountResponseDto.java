package com.example.nefix.referraldiscount;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReferralDiscountResponseDto
{
    private Long referralDiscountId;
    private String link; // Referral link/code
}
