package com.example.nefix.referraldiscount;

import com.example.nefix.account.Account;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class ReferralDiscount
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "referral_discount_id")
    private Long referralDiscountId;

    @OneToOne(mappedBy = "referralDiscount")
    private Account account;

    @JsonProperty("link")
    @Pattern(regexp = "^(https?://)?([\\w.-]+)+(:\\d+)?(/\\S*)?$", message = "Invalid link format.")
    private String link;
}
