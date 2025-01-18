package com.example.nefix.blockedaccount;

import com.example.nefix.genrealization.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlockedAccountService extends BaseService<BlockedAccount, Long>
{
    public BlockedAccountService(BlockedAccountsRepository repository)
    {
        super(repository, List.of("blockedAccountId"));
    }
}

