package com.example.redisdemo.repository;

import com.example.redisdemo.type.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, String> {

}
