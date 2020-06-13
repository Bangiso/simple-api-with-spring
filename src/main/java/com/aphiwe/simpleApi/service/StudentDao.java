package com.aphiwe.simpleApi.service;

import com.aphiwe.simpleApi.model.Student;

import java.util.List;

public interface StudentDao {
    public int insertStudent(Student student);

    public List<Student> fetchStudents();


}