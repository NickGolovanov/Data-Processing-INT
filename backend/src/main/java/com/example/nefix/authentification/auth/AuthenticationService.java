package com.example.nefix.authentification.auth;

import com.example.nefix.account.Account;
import com.example.nefix.account.AccountRepository;
import com.example.nefix.authentification.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService
{
    private final AccountRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request)
    {
        Account account = new Account();
        account.setEmail(request.getEmail());
        account.setPassword(this.passwordEncoder.encode(request.getPassword()));

        this.repository.save(account);

        String jwtToken = this.jwtService.generateToken(account);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request)
    {
        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (request.getEmail(),
                        request.getPassword()
                ));
        Account account = repository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("Account not found"));
        String jwtToken = jwtService.generateToken(account);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
