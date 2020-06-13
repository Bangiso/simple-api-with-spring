package com.aphiwe.simpleApi.api;

import com.aphiwe.simpleApi.model.Student;
import com.aphiwe.simpleApi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentDao studentDaoService;

    public StudentController(StudentDao studentDaoService) {
        this.studentDaoService = studentDaoService;
    }

    @GetMapping
    public @ResponseBody
    String index() {
        return "Welcome to students list";
    }

    @GetMapping("all")
    public @ResponseBody
    List<Student> fetchStudent() {
        return studentDaoService.fetchStudents();
    }

    @PostMapping("/add")
    public @ResponseBody
    int addStudent(@RequestBody Student student) {

        return studentDaoService.insertStudent(student);
    }
}
