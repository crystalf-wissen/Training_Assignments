package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.ClassTeacher;
import com.example.demo.models.Student;

@Repository
public interface ClassTeacherRepository extends JpaRepository<ClassTeacher, Integer> {
	public List<ClassTeacher> findByStandard(int standard);
}
