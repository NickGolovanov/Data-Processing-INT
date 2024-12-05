package com.example.nefix.referraldiscount;

import com.example.nefix.genrealization.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/referral-discount")
public class ReferralDiscountController extends BaseController<ReferralDiscount, Long> {
    public ReferralDiscountController(ReferralDiscountService service) {
        super(service);
    }
}
