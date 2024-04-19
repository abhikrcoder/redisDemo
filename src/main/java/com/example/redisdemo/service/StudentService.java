package com.example.redisdemo.service;

import com.example.redisdemo.repository.StudentRepository;
import com.example.redisdemo.type.Student;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {
    //private final StudentRepository studentRepository;

    private final List<Student> students = new ArrayList<>(List.of(Student.builder().id("1223").build()));

    public Student saveNewStudent(@NonNull Student student) {
        students.add(student);
        return student;
    }

    public Student retrieveStudent(@NonNull String id) {
        return students.stream().filter(f-> f.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Student> retrieveAllStudents() {
        return students;
    }
    public Boolean deleteStudent(String id) {
        return students.removeIf(f-> f.getId().equals(id));
    }
}
