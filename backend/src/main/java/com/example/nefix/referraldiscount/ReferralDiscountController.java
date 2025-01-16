package com.example.nefix.referraldiscount;

import com.example.nefix.genrealization.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/referral-discount")
public class ReferralDiscountController extends BaseController<ReferralDiscount, Long> {
    public ReferralDiscountController(ReferralDiscountService service) {
        super(service);
    }
}
