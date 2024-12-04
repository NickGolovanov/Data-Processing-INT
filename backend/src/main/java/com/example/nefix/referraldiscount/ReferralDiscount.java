package com.example.nefix.referraldiscount;

import com.example.nefix.account.Account;
import jakarta.persistence.*;

@Entity
public class ReferralDiscount
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long referralDiscountId;

    @OneToOne(mappedBy = "accountId")
    private Account account;

    private String link;
}
