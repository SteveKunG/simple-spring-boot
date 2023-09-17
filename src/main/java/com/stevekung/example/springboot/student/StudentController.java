package com.stevekung.example.springboot.student;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController
{
    private final StudentService service;

    public StudentController(StudentService service)
    {
        this.service = service;
    }

    @GetMapping
    public List<Student> getStudents()
    {
        return this.service.getStudents();
    }

    @PostMapping
    public void add(@RequestBody Student student)
    {
        this.service.add(student);
    }

    @PutMapping(path = "{student_id}")
    public void update(@PathVariable("student_id") Long id, @RequestParam(required = false) String name, @RequestParam(required = false) String email, @RequestParam(required = false) LocalDate dateOfBirth)
    {
        this.service.update(id, name, email, dateOfBirth);
    }

    @DeleteMapping(path = "{student_id}")
    public void delete(@PathVariable("student_id") Long id)
    {
        this.service.delete(id);
    }
}