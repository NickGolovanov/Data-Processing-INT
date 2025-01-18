package com.example.nefix;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig
{
    @Bean
    public OpenAPI customOpenApi()
    {
        return new OpenAPI()
                .servers(List.of(
                        new Server().url("http://localhost:8080/v3/api-docs").description("Local Server")
                ))
                .info(new Info()
                        .title("Nefix")
                        .version("1.0")
                        .description("This is the API Documentation"));
    }
}
