package com.example.nefix.accountsubscription;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AccountSubscriptionRepository extends JpaRepository<AccountSubscription, AccountSubscriptionId>
{
    AccountSubscription getByAccount_AccountIdAndSubscription_SubscriptionId(Long accountAccountId, Long subscriptionSubscriptionId);

    @Query(value = "CALL add_subscription(:p_account_id, :p_subscription_id, :p_date_of_purchase, :p_date_of_expire, null)", nativeQuery = true)
    long callAddSubscription(@Param("p_account_id") Long p_account_id,
                             @Param("p_subscription_id") Long p_subscription_id,
                             @Param("p_date_of_purchase") LocalDate p_date_of_purchase,
                             @Param("p_date_of_expire") LocalDate p_date_of_expire);

    @Modifying
    @Query(value = "CALL delete_subscription(:p_account, :p_subscription)", nativeQuery = true)
    void callDeleteSubscription(
            @Param("p_account") Long p_account,
            @Param("p_subscription") Long p_subscription);

    @Modifying
    @Query(value = "CALL update_subscription(:p_account, :p_subscription_id, :p_date_of_purchase, :p_date_of_expire)", nativeQuery = true)
    void callUpdateSubscription(
            @Param("p_account") Long p_account,
            @Param("p_subscription_id") Long p_subscription_id,
            @Param("p_date_of_purchase") LocalDate p_date_of_purchase,
            @Param("p_date_of_expire") LocalDate op_date_of_expire);

}
