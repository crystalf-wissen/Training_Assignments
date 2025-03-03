package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{
	public List<Student> findBySchool(String school);
	public List<Student> findByPercentageGreaterThan(double percentage);
	public List<Student> findByPercentageLessThan(double percentage);
	public List<Student> findByStandard(int standard);
}
