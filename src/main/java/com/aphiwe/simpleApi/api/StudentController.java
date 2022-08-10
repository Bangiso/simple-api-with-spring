package com.aphiwe.simpleApi.api;

import com.aphiwe.simpleApi.service.StudentService;
import com.aphiwe.simpleApi.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@CrossOrigin(origins = "*")
@RequestMapping()
public class StudentController {
    @Autowired
    private StudentService studentService;

    public StudentController(StudentService studentDaoService) {
        this.studentService = studentDaoService;
    }

    @GetMapping("/")
    public @ResponseBody
    String index() {
        return "Welcome to students list";
    }

    @GetMapping("/students")
    public @ResponseBody
    List<Student> fetchStudents() {
        return studentService.fetchStudents();
    }

    @GetMapping("/students/{id}")
    public @ResponseBody
    Optional<Student> fetchStudent(@PathVariable int id) {
        return studentService.findById(id);
    }

    @PostMapping("/students")
    public @ResponseBody
    int addStudent(@RequestBody Student student) {
        return studentService.save(student);
    }

    @PutMapping("/students")
    public @ResponseBody
    int updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }

    @DeleteMapping("/students/{id}")
    public @ResponseBody
    int deleteStudent(@PathVariable int id) {
        return studentService.deleteStudent(id);
    }
}
