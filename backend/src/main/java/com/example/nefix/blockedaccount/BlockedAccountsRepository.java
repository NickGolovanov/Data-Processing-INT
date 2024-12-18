package com.example.nefix.blockedaccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockedAccountsRepository extends JpaRepository<BlockedAccount, Long> {
}
