package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
//@RequiredArgsConstructor // Kullanirsam @Autowired yazmama gerek kalmaz.
public class StudentController {
    @Autowired
    private StudentService studentService;


    @GetMapping
    //Response Body ve HTTP Status Code gonderebilmek icin ResponseEntity kullaniyorum ve Generic yapidadir.
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> allStudent= studentService.getAllStudent();
        return new ResponseEntity<>(allStudent, HttpStatus.OK);
    }

}
