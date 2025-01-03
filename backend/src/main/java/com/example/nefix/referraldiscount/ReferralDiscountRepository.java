package com.example.nefix.referraldiscount;

import com.example.nefix.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReferralDiscountRepository extends JpaRepository<ReferralDiscount, Long> {
    List<ReferralDiscount> findByAccount(Account account);
}
