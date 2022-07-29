package com.aphiwe.simpleApi.service;

import com.aphiwe.simpleApi.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    public int save(Student student);
    public List<Student> fetchStudents();
    public int updateStudent(Student student);
    public int deleteStudent(int id);
    public Optional<Student> findById(int id);
}
