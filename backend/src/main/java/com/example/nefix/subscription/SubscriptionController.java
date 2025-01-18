package com.example.nefix.subscription;

import com.example.nefix.genrealization.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/subscription")
public class SubscriptionController extends BaseController<Subscription, Long>
{
    public SubscriptionController(SubscriptionService service)
    {
        super(service);
    }
}