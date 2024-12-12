package com.example.nefix;

import com.example.nefix.authentification.auth.AuthenticationService;
import com.example.nefix.authentification.auth.RegisterRequest;
import com.example.nefix.authentification.user.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.example.nefix.authentification.user.Role.MEDIOR;
import static com.example.nefix.authentification.user.Role.SENIOR;

@SpringBootApplication
public class NefixApplication {

    public static void main(String[] args) {
        SpringApplication.run(NefixApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AuthenticationService service)
    {
        return args ->
        {
            var senior = RegisterRequest.builder()
                    .firstname("Senior")
                    .lastname("Senior")
                    .email("senior@mail.com")
                    .password("password")
                    .role(SENIOR)
                    .build();
            System.out.println("Senior token: " + service.register(senior).getToken());

            var medior = RegisterRequest.builder()
                    .firstname("Medior")
                    .lastname("Medior")
                    .email("medior@mail.com")
                    .password("password")
                    .role(MEDIOR)
                    .build();
            System.out.println("Medior token: " + service.register(medior).getToken());
        };
    }
}
