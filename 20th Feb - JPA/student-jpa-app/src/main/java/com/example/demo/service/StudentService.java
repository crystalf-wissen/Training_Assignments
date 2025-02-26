package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.model.Student;
import com.example.demo.repository.h2.H2StudentRepository;
import com.example.demo.repository.postgres.PostgresStudentRepository;

@Service
public class StudentService {

    @Autowired
    private H2StudentRepository h2Repository;

    @Autowired
    private PostgresStudentRepository postgresRepository;

    @Transactional("h2TransactionManager")
    public void saveToH2(Student student) {
        h2Repository.save(student);
    }

    @Transactional("postgresTransactionManager")
    public void saveToPostgres(Student student) {
        postgresRepository.save(student);
    }

    @Transactional
    public void saveStudent(Student student) {
        saveToH2(student);
        saveToPostgres(student);
    }
}
