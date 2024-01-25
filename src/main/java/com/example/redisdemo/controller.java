package com.example.redisdemo;

import com.example.redisdemo.service.StudentService;
import com.example.redisdemo.type.Student;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class controller {

    private final StudentService service;

    @GetMapping("/student/{id}")
    Student getStudentById(@PathVariable("id") String id) {
        return service.retrieveStudent(id);
    }
    
    @GetMapping("/students")
    List<Student> getAllStudents() {
        return service.retrieveAllStudents();
    }
    
    @PutMapping("/student")
    Student saveStudent(@RequestBody Student student) {
        return service.saveNewStudent(student);
    }

    
}
