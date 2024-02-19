package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;
import com.tpe.service.StudentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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


    Logger logger= LoggerFactory.getLogger(StudentController.class);


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
    //@PathVariable ile yapmam gerekirse @GetMapping("/{id}) olarak almam lazim . /1 seklinde url olusur
    //@RequestParam ile yapmam gerekirse @GetMapping(**path**) ?id=1 seklinde url olusur
    public ResponseEntity<Student> getStudentWithId(@RequestParam Long studentId){
        Student student =studentService.getStudentById(studentId);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> deleteStudent(@PathVariable("id") Long id){
        studentService.deleteStudent(id);
        Map<String, String> response= new HashMap<>();
        response.put("message","Student is deleted successfully ");
        response.put("status","success");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Map<String,String>> updateStudent(@PathVariable("id") Long id,
                                              @Valid @RequestBody StudentDTO studentDto){

        studentService.updateStudent(id,studentDto);

        Map<String, String> response= new HashMap<>();
        response.put("message","Student is updated successfully ");
        response.put("status","success");
        return ResponseEntity.ok(response);
    }
    @GetMapping("/page")
    public ResponseEntity<Page<Student>> getAllStudentByPage
            (@RequestParam(value = "page",required = false,defaultValue = "0") int page,
             @RequestParam(value = "size",required = false,defaultValue = "2") int size,
             @RequestParam("sort") String prop,
             @RequestParam("direction") Sort.Direction direction)
    {
        Pageable pageable= PageRequest.of(page,size, Sort.by(direction,prop));
        Page<Student> studentsByPage=studentService.getAllStudentPaging(pageable);
        return new ResponseEntity<>(studentsByPage,HttpStatus.OK);
    }

    @GetMapping("/{grade}")
    public ResponseEntity<List<Student>> getStudentByGrade(@PathVariable("grade") Integer grade){
        List<Student> students=studentService.getAllStudentEqualsGrade(grade);
        return new ResponseEntity<>(students,HttpStatus.OK);
    }

    @GetMapping("/studentDto/{id}")
    public ResponseEntity<StudentDTO> getStudentDtoId(@PathVariable("id") Long id){
        StudentDTO studentDto=studentService.getStudentDtoById(id);
        logger.warn("Servisten Student DTO objesi alindi." + studentDto.getName());
        return ResponseEntity.ok(studentDto);
    }






}
