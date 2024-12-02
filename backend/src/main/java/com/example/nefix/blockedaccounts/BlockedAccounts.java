package com.example.nefix.blockedaccounts;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class BlockedAccounts
{
    @Id
    private Long id;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
}
