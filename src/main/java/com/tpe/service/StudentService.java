package com.tpe.service;


import com.tpe.domain.Student;
import com.tpe.exception.ConflictException;
import com.tpe.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudent() {
        List<Student> allStudent= studentRepository.findAll(); // select * from student;
        return allStudent;
    }

    public void saveStudent(Student student) {
        if (studentRepository.existsByEmail(student.getEmail())){
            throw  new ConflictException("Emails is already exist");
        }
        studentRepository.save(student);





    }
}
