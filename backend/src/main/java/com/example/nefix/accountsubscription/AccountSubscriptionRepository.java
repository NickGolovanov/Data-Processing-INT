package com.example.nefix.accountsubscription;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountSubscriptionRepository extends JpaRepository<AccountSubscription, Long> {
}
