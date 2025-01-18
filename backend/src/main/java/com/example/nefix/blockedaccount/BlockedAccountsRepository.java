package com.example.nefix.blockedaccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BlockedAccountsRepository extends JpaRepository<BlockedAccount, Long>
{
    @Query(value = "CALL block_account(:accountId, :isPermanent, :dateOfExpire, null)", nativeQuery = true)
    Long callBlockAccount(
            @Param("accountId") Long accountId,
            @Param("isPermanent") boolean isPermanent,
            @Param("dateOfExpire") LocalDate dateOfExpire
    );

    @Modifying
    @Query(value = "CALL unblock_account(:accountId)", nativeQuery = true)
    void callUnblockAccount(
            @Param("accountId") Long accountId
    );

    Optional<BlockedAccount> findByAccount_AccountId(Long accountId);

    List<BlockedAccount> getBlockedAccountsByAccount_AccountId(Long accountAccountId);
}
