package com.example.nefix;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NefixApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(NefixApplication.class, args);
    }
}
