package com.example.nefix.blockedaccount;

import com.example.nefix.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlockedAccountsRepository extends JpaRepository<BlockedAccount, Long>
{
    Optional<BlockedAccount> findByAccount_AccountId(Long accountId);
}
