package com.stevekung.example.springboot.student;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

import org.springframework.core.style.ToStringCreator;

import jakarta.persistence.*;

@Entity
@Table
public class Student
{
    private static final String STUDENT_SEQUENCE = "student_sequence";

    @Id
    @SequenceGenerator(name = Student.STUDENT_SEQUENCE, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Student.STUDENT_SEQUENCE)
    private Long id;

    private String name;
    private String email;
    private LocalDate dateOfBirth;

    @Transient
    private int age;

    public Student()
    {}

    public Student(String name, String email, LocalDate dateOfBirth)
    {
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString()
    {
        return new ToStringCreator(this)
                .append("id", this.id)
                .append("name", this.name)
                .append("email", this.email)
                .append("dateOfBirth", this.dateOfBirth.toString())
                .append("age", this.age)
                .toString();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (!(obj instanceof Student student))
        {
            return false;
        }
        return Objects.equals(this.id, student.id) && Objects.equals(this.name, student.name) && Objects.equals(this.email, student.email) && Objects.equals(this.dateOfBirth, student.dateOfBirth) && Objects.equals(this.age, student.age);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.id, this.name, this.email, this.dateOfBirth, this.age);
    }

    public Long getId()
    {
        return this.id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public LocalDate getDateOfBirth()
    {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge()
    {
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }

    public void setAge(int age)
    {
        this.age = age;
    }
}