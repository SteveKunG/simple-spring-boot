package com.stevekung.example.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController
{
    @GetMapping("/")
    public String index()
    {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/aaa")
    public String aaa()
    {
        return "AAAAAA";
    }

    @GetMapping("/hello")
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name)
    {
        return "Hello, %s!".formatted(name);
    }
}