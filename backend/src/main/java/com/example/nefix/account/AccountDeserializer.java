package com.example.nefix.account;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AccountDeserializer extends JsonDeserializer<Account>
{
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException
    {
        Long accountId = jsonParser.getValueAsLong();

        return accountRepository.findById(accountId).orElseThrow(() -> new IllegalArgumentException("Account with ID " + accountId + " not found"));
    }
}