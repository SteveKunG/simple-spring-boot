package com.stevekung.example.springboot.student;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import jakarta.transaction.Transactional;

@Service
public class StudentService
{
    private static final Pattern VALID_USERNAME_PATTERN = Pattern.compile("^[A-Za-z0-9]\\w{5,29}$");
    private static final Pattern VALID_EMAIL_PATTERN = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");

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
            throw new RuntimeException("Email already taken!");
        }

        if (!checkValidUsername(student) || !checkValidEmail(student))
        {
            return;
        }

        LOGGER.info("Saving Student: {}", student);
        this.repository.save(student);
    }

    @Transactional
    public void update(Long id, String name, String email, LocalDate dateOfBirth)
    {
        var student = this.repository.findById(id).orElseThrow(() -> new RuntimeException("Student ID with '" + id + "' does not exist!"));

        if (StringUtils.hasText(name) && checkValidUsername(student))
        {
            if (!Objects.equals(student.getName(), name))
            {
                LOGGER.info("Update Name from '{}' to '{}'", student.getName(), name);
                student.setName(name);
            }
        }

        if (StringUtils.hasText(email) && checkValidEmail(student))
        {
            var optional = this.repository.findStudentByEmail(email);

            if (optional.isPresent())
            {
                throw new RuntimeException("Email already taken!");
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
            throw new RuntimeException("Student ID with '" + id + "' does not exist!");
        }
        LOGGER.info("Deleting Student by ID: {}", id);
        this.repository.deleteById(id);
    }

    private static boolean checkValidUsername(Student student)
    {
        var usernameMatcher = VALID_USERNAME_PATTERN.matcher(student.getName());

        if (!usernameMatcher.matches())
        {
            LOGGER.error("Invalid Student Name: {}", student.getName());
            throw new RuntimeException("Invalid username format! Must be 6-30 characters.");
        }
        return true;
    }

    private static boolean checkValidEmail(Student student)
    {
        var emailMatcher = VALID_EMAIL_PATTERN.matcher(student.getEmail());

        if (!emailMatcher.matches())
        {
            LOGGER.error("Invalid Student Email: {}", student.getEmail());
            throw new RuntimeException("Invalid email format!");
        }
        return true;
    }
}