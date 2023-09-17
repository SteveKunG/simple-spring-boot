package com.stevekung.example.springboot.student;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig
{
    @Bean
    CommandLineRunner runner(StudentRepository repository)
    {
        return args ->
        {
            var aaa = new Student("AAA", "AAA@gmail.com", LocalDate.of(1999, 2, 2));
            var bbb = new Student("BBB", "BBB@gmail.com", LocalDate.of(2006, 8, 31));
            repository.saveAll(List.of(aaa, bbb));
        };
    }
}