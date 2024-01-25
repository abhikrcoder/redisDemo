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
    private final StudentRepository studentRepository;

    public Student saveNewStudent(@NonNull Student student) {
        return studentRepository.save(student);
    }

    public Student retrieveStudent(@NonNull String id) {
        Student retrievedStudent =
                studentRepository.findById(id).get();
        return retrievedStudent;
    }

    public List<Student> retrieveAllStudents() {
        List<Student> studentsList = new ArrayList<>();
        studentRepository.findAll()
        .forEach(studentsList::add);
        return studentsList;
    }
}
