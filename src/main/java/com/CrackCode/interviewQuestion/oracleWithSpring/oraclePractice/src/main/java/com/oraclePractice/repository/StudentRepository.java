package com.oraclePractice.repository;

import com.oraclePractice.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

public interface StudentRepository extends JpaRepository<Student,Long> {

    @Procedure("getallstudent")
    Object getAllStudents();
}
