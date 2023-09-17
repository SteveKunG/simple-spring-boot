package com.stevekung.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application
{
    // `\c student` in PSQL first!

    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }
}