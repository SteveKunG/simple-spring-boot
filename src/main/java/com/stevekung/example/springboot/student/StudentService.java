package com.stevekung.example.springboot.student;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import jakarta.transaction.Transactional;

@Service
public class StudentService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository repository;

    public StudentService(StudentRepository repository)
    {
        this.repository = repository;
    }

    public List<Student> getStudents()
    {
        return this.repository.findAll();
    }

    public void add(Student student)
    {
        var optional = this.repository.findStudentByEmail(student.getEmail());

        if (optional.isPresent())
        {
            throw new IllegalStateException("Email already taken!");
        }
        LOGGER.info("Saving Student: {}", student);
        this.repository.save(student);
    }

    @Transactional
    public void update(Long id, String name, String email, LocalDate dateOfBirth)
    {
        var student = this.repository.findById(id).orElseThrow(() -> new IllegalStateException("Student ID with '" + id + "' does not exist!"));

        if (StringUtils.hasText(name) && !Objects.equals(student.getName(), name))
        {
            LOGGER.info("Update Name from '{}' to '{}'", student.getName(), name);
            student.setName(name);
        }

        if (StringUtils.hasText(email))
        {
            var optional = this.repository.findStudentByEmail(email);

            if (optional.isPresent())
            {
                throw new IllegalStateException("Email already taken!");
            }
            if (!Objects.equals(student.getEmail(), email))
            {
                LOGGER.info("Update Email from '{}' to '{}'", student.getEmail(), email);
                student.setEmail(email);
            }
        }

        if (Objects.nonNull(dateOfBirth) && !dateOfBirth.isEqual(student.getDateOfBirth()))
        {
            LOGGER.info("Update Date of Birth from '{}' to '{}'", student.getDateOfBirth(), dateOfBirth);
            student.setDateOfBirth(dateOfBirth);
        }
    }

    public void delete(Long id)
    {
        if (!this.repository.existsById(id))
        {
            throw new IllegalStateException("Student ID with '" + id + "' does not exist!");
        }
        LOGGER.info("Deleting Student by ID: {}", id);
        this.repository.deleteById(id);
    }
}