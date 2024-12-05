package com.example.nefix.referraldiscount;

import com.example.nefix.genrealization.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class ReferralDiscountService extends BaseService<ReferralDiscount, Long> {
     public ReferralDiscountService(ReferralDiscountRepository repository) {
        super(repository);
    }
}
