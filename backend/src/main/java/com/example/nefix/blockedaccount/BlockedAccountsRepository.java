package com.example.nefix.blockedaccount;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockedAccountsRepository extends JpaRepository<BlockedAccount, Long> {
}
