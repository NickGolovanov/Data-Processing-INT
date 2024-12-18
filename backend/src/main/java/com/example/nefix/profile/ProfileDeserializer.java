package com.example.nefix.profile;

import com.example.nefix.account.Account;
import com.example.nefix.account.AccountRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ProfileDeserializer extends JsonDeserializer<Profile>
{
    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Profile deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException
    {
        Long profileId = jsonParser.getValueAsLong();

        return profileRepository.findById(profileId).orElseThrow(() -> new IllegalArgumentException("Profile with ID " + profileId + " not found"));
    }
}