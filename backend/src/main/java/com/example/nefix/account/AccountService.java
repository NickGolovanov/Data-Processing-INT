package com.example.nefix.account;

import com.example.nefix.genrealization.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class AccountService extends BaseService<Account, Long>
{
    public AccountService(AccountRepository repository)
    {
        super(repository);
    }
}
