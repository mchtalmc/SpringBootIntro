package com.tpe.service;


import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;
import com.tpe.exception.ConflictException;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Student getStudentById(Long studentId) {
        Student student=studentRepository.findById(studentId).orElseThrow(()->
            new ResourceNotFoundException("Student not found by id" + studentId));
        return student;
    }

    public void deleteStudent(Long id) {
        Student student= getStudentById(id);
        studentRepository.delete(student);
    }

    public void updateStudent(Long id, StudentDTO studentDto) {
        Student student= getStudentById(id);
        boolean existEmail = studentRepository.existsByEmail(studentDto.getEmail());

        if (existEmail && !student.getEmail().equals(studentDto.getEmail())){
            throw new ConflictException("Email already exist"+studentDto.getEmail());

        }
        student.setName(studentDto.getName());
        student.setLastname(studentDto.getLastname());
        student.setPhoneNumber(studentDto.getPhoneNumber());
        student.setEmail(studentDto.getEmail());
        studentRepository.save(student);
    }

    public Page<Student> getAllStudentPaging(Pageable pageable) {

        return studentRepository.findAll(pageable);

    }

    public List<Student> getAllStudentEqualsGrade(Integer grade) {
        return studentRepository.findAllGradeEquals(grade);
    }

 //   public StudentDto getStudentDtoById(Long id) {

  //      Student student=getStudentById(id);
 //       //Dto class'inda constructor ile mapleme yapmak istersem.
 //       StudentDto studentDto=new StudentDto(student);
 //       return studentDto;
//
 //   }

    public StudentDTO getStudentDtoById(Long id) {
        StudentDTO studentDTO=studentRepository.findStudentDtoById(id).orElseThrow(()->
                new ResourceNotFoundException("Student not found by id: "+id));
        return studentDTO;
    }
}
