package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.dto.StudentDto;
import com.tpe.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
//@RequiredArgsConstructor // Kullanirsam @Autowired yazmama gerek kalmaz.
public class StudentController {
    @Autowired //Injection islemi yapmak icin kullaniyorum .
    private StudentService studentService;


    @GetMapping
    //Response Body ve HTTP Status Code gonderebilmek icin ResponseEntity kullaniyorum ve Generic yapidadir.
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> allStudent= studentService.getAllStudent();
        return new ResponseEntity<>(allStudent, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<String,String>> createStudent (@Valid @RequestBody Student student){

        studentService.saveStudent(student);
        Map<String,String> response= new HashMap<>();
        response.put("message", "Students is created successfully");
        response.put("status","success");
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping("/query")
    //@PathParam ile yapmam gerekirse @GetMapping("/{id}) olarak almam lazim . /1 seklinde url olusur
    //@RequestParam ile yapmam gerekirse @GetMapping(**path**) ?id=1 seklinde url olusur
    public ResponseEntity<Student> getStudentWithId(@RequestParam Long studentId){
        Student student =studentService.getStudentById(studentId);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @DeleteMapping("/id")
    public ResponseEntity<Map<String,String>> deleteStudent(@RequestParam("id") Long id){
        studentService.deleteStudent(id);
        Map<String, String> response= new HashMap<>();
        response.put("message","Student is deleted successfully ");
        response.put("status","success");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<Map<String,String>> updateStudent(@PathVariable("id") Long id,
                                              @Valid @RequestBody StudentDto studentDto){

        Map<String, String> response= new HashMap<>();
        response.put("message","Student is updated successfully ");
        response.put("status","success");
        return ResponseEntity.ok(response);
    }

}
