package com.example.nefix.subscription;

import com.example.nefix.genrealization.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService extends BaseService<Subscription, Long> {
    public SubscriptionService(SubscriptionRepository repository) {
        super(repository);
    }
}
