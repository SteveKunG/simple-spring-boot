package com.stevekung.example.springboot.student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>
{
    @Query("SELECT student FROM Student student WHERE student.email = ?1")
    Optional<Student> findStudentByEmail(String email);
}