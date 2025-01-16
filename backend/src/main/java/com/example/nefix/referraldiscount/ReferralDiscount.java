package com.example.nefix.referraldiscount;

import com.example.nefix.account.Account;
import com.example.nefix.blockedaccount.BlockedAccount;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
public class ReferralDiscount implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "referral_discount_id")
    private Long referralDiscountId;

    @OneToOne
    @JsonIgnore
    private Account account;

    @JsonProperty("link")
    @Pattern(regexp = "^(https?://)?([\\w.-]+)+(:\\d+)?(/\\S*)?$", message = "Invalid link format.")
    private String link;

    @JsonProperty(value = "accountId", access = JsonProperty.Access.READ_ONLY)
    private Long accountId(){
        if (this.account == null) {
            return null;
        }

        return this.account.getAccountId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReferralDiscount that = (ReferralDiscount) o;
        return Objects.equals(this.referralDiscountId, that.referralDiscountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.referralDiscountId);
    }
}
