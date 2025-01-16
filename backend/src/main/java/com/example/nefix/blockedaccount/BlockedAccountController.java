package com.example.nefix.blockedaccount;
import com.example.nefix.genrealization.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/blocked-account")
public class BlockedAccountController extends BaseController<BlockedAccount, Long>
{
    public BlockedAccountController(BlockedAccountService service)
    {
        super(service);
    }
}
