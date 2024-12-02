package com.example.nefix.referraldiscount;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ReferralDiscount
{
    @Id
    private Long referralDiscountId;

    public void setReferralDiscountId(Long referralDiscountId)
    {
        this.referralDiscountId = referralDiscountId;
    }

    public Long getReferralDiscountId()
    {
        return referralDiscountId;
    }
}
