package com.example.demo.repository.h2;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Student;

public interface H2StudentRepository extends JpaRepository<Student, Integer> {
}
