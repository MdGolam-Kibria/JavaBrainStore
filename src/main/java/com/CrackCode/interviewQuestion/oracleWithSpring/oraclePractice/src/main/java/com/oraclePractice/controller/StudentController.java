package com.oraclePractice.controller;

import com.oraclePractice.model.Student;
import com.oraclePractice.repository.StudentRepository;
import com.oraclePractice.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StudentController {
    private final StudentRepository studentRepository;
    private final StudentService studentService;


    public StudentController(StudentRepository studentRepository, StudentService studentService) {
        this.studentRepository = studentRepository;
        this.studentService = studentService;
    }

    //call store procedure here
    @GetMapping("/create")
    public String createStudent(){
        studentService.createStudent();
        return "ok";
    }


    @GetMapping("/getAll")
    public Object getAllStudent() {
        return studentRepository.getAllStudents();
    }

    @GetMapping("/")
    public void insertStudent() {


        Student student = new Student();
        student.setName("kibria");
        student.setEmail("email");
        student.setPhone("01771598949");
        student.setPhone("0177");

        studentRepository.save(student);
    }
}
