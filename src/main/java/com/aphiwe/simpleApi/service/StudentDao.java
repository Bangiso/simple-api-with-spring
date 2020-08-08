package com.aphiwe.simpleApi.service;

import com.aphiwe.simpleApi.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentDao {
    public int save(Student student);
    public List<Student> fetchStudents();
    public int updateStudent(int id, Student student);
    public Optional<Student> findById(int id);
}
