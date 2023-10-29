package com.stevekung.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application
{
    // `\c student` in PSQL first!
    // Change H2 Driver class to : org.hibernate.dialect.H2Dialect
    // JDBC URL : jdbc:postgresql://localhost:5432/student
    // Username : postgres

    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }
}