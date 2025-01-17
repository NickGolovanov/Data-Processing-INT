package com.example.nefix.authentification.auth;

import com.example.nefix.account.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest
{
    @NotNull
    private String email;

    @NotNull
    private String password;
}