package com.example.nefix.account;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Account
{
    @Id
    private Long accountId;

    public void setAccountId(Long accountId)
    {
        this.accountId = accountId;
    }

    public Long getAccountId()
    {
        return accountId;
    }
}
