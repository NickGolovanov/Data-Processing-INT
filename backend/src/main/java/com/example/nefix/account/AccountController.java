package com.example.nefix.account;

import com.example.nefix.genrealization.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController extends BaseController<Account, Long>
{
    public AccountController(AccountService service)
    {
        super(service);
    }
}
