package com.example.nefix.blockedaccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlockedAccountsRepository extends JpaRepository<BlockedAccount, Long>
{
    Optional<BlockedAccount> findByAccount_AccountId(Long accountId);

    List<BlockedAccount> getBlockedAccountsByAccount_AccountId(Long accountAccountId);
}
