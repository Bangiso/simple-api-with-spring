package com.aphiwe.simpleApi.dao;

import com.aphiwe.simpleApi.model.Student;
import com.aphiwe.simpleApi.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.*;

import java.util.List;
import java.util.Optional;

@Repository
public class StudentDao implements StudentService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    Logger logger = LoggerFactory.getLogger(StudentDao.class);

    @Override
    public int save(Student student) {
        String sql = "INSERT INTO students (id, name, gpa) VALUES (?, ?, ?)";
        int result = jdbcTemplate.update(sql, student.getId(), student.getName(), student.getGpa());
        if (result > 0) {
            logger.info("new student added");
        }
        return 204;
    }

    @Override
    public List<Student> fetchStudents() {
        String sql = "SELECT * FROM students";
        List<Student> students = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper(Student.class));
        return students;
    }

    @Override
    public int updateStudent(Student student) {
        String sql = "UPDATE students  set name = ?, gpa = ? where id = ?";
        int result = jdbcTemplate.update(sql, student.getName(), student.getGpa(), student.getId());
        if (result > 0) {
            logger.info("student updated.");
            return 204;
        } else {
            return save(student);
        }
    }
    @Override
    public int deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id = ?";
        int result = jdbcTemplate.update(sql, id);
        if (result > 0) {
            logger.info("student deleted.");
        }
        return 204;
    }

    @Override
    public Optional<Student> findById(int id) {
        try {
            String sql = "SELECT * FROM students WHERE id = ?";
            return Optional.ofNullable(
                    (Student)  jdbcTemplate
                            .queryForObject(
                                    sql,
                                    new Object[]{id},
                                    new BeanPropertyRowMapper(Student.class)
                            ));
        } catch (EmptyResultDataAccessException ex){
            logger.info("No student found with id " + id);
            return null;
        }
    }

}