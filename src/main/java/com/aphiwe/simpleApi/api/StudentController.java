package com.aphiwe.simpleApi.api;

import com.aphiwe.simpleApi.dao.StudentDao;
import com.aphiwe.simpleApi.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


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
    List<Student> fetchStudents() {
        return studentDaoService.fetchStudents();
    }

    @GetMapping("/{id}")
    public @ResponseBody
    Optional<Student> fetchStudent(@PathVariable int id) {
        return studentDaoService.findById(id);
    }

    @PostMapping
    public @ResponseBody
    int addStudent(@RequestBody Student student) {
        return studentDaoService.save(student);
    }

    @PutMapping
    public @ResponseBody
    int updateStudent(@RequestBody Student student) {
        return studentDaoService.updateStudent(student);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody
    int deleteStudent(@PathVariable int id) {
        return studentDaoService.deleteStudent(id);
    }
}
