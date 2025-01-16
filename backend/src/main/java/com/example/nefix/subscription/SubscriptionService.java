package com.example.nefix.subscription;

import com.example.nefix.genrealization.service.BaseService;
import com.example.nefix.referraldiscount.ReferralDiscount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService extends BaseService<Subscription, Long> {
    public SubscriptionService(SubscriptionRepository repository) {
                super(repository, List.of("subscriptionId", "accounts"));
    }
}
