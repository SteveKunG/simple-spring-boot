package com.stevekung.example.springboot;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stevekung.example.springboot.student.Student;

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

    @GetMapping("/list_student")
    public List<Student> listStudent()
    {
        //@formatter:off
        return List.of(
                new Student("AAA", "AAA@gmail.com", LocalDate.of(1999, 2, 2)),
                new Student("BBB", "BBB@gmail.com", LocalDate.of(2006, 8, 31)));
        //@formatter:on
    }
}