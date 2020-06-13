package com.aphiwe.simpleApi.service;

import com.aphiwe.simpleApi.model.Student;
import org.springframework.stereotype.*;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentService implements StudentDao {
    private final List<Student> students = new ArrayList<>();

    @Override
    public int insertStudent(Student student) {
        if (students.contains(student)) {
            return 201;
        }
        students.add(student);
        return 204;
    }

    @Override
    public List<Student> fetchStudents() {
        if (students.isEmpty()) {
            students.add(new Student(1, "Aphiwe", 45.5));
            return students;
        } else {
            return students;
        }
    }

}