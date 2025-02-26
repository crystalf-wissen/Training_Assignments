package com.example.demo.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Student;

public interface PostgresStudentRepository extends JpaRepository<Student, Integer> {
}
