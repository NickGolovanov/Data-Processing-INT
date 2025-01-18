package com.example.nefix.referraldiscount;

import com.example.nefix.genrealization.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReferralDiscountService extends BaseService<ReferralDiscount, Long>
{
    public ReferralDiscountService(ReferralDiscountRepository repository)
    {
        super(repository, List.of("referralDiscountId"));
    }
}
