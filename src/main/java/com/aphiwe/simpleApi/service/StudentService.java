package com.aphiwe.simpleApi.service;

import com.aphiwe.simpleApi.model.Student;
import org.springframework.stereotype.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentService implements StudentDao {
    private final List<Student> students = new ArrayList<>();

    @Override
    public int save(Student student) {
        if (students.contains(student)) {
            return 204;
        }
        students.add(student);
        return 204;
    }

    @Override
    public List<Student> fetchStudents() {
        if (students.isEmpty()) {
            students.add(new Student(1, "Aphiwe", 45.5));
        }
        return students;
    }

    @Override
    public int updateStudent(int id, Student student) {
        if(findById(student.getId()).isPresent() && id==student.getId()){
             students.remove(findById(id).get());
             students.add(student);
           };
        return 204;
    }

    @Override
    public Optional<Student> findById(int id) {
        return students.stream().filter(student1 -> student1.getId()==id).findFirst();
    }

}