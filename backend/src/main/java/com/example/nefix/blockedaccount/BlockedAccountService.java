package com.example.nefix.blockedaccount;
import com.example.nefix.genrealization.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class BlockedAccountService extends BaseService<BlockedAccount, Long>
{
    public BlockedAccountService(BlockedAccountsRepository repository)
    {
        super(repository);
    }
}

