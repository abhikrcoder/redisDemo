package com.example.redisdemo;

import com.example.redisdemo.service.StudentService;
import com.example.redisdemo.type.Student;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    @Cacheable(value="Students", unless="#result==null")
    @GetMapping("/student/{id}")
    public Student getStudentById(@PathVariable("id") String id) {
        return service.retrieveStudent(id);
    }
    
    @GetMapping("/students")
    List<Student> getAllStudents() {
        return service.retrieveAllStudents();
    }

    @CachePut(cacheNames ="Students", key = "#p0.id")
    @PutMapping("/student")
    public Student saveStudent(@RequestBody Student student) {
        return service.saveNewStudent(student);
    }

    @CacheEvict("Students")
    @DeleteMapping("/student/{id}")
    public Boolean deleteStudent(@PathVariable("id") String id) {
        return service.deleteStudent(id);
    }
}
