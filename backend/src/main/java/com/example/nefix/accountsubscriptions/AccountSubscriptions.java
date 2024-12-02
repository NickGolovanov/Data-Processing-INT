package com.example.nefix.accountsubscriptions;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AccountSubscriptions
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
