package com.registationSystem.regSys.Controllers;

import com.registationSystem.regSys.Models.Student;
import com.registationSystem.regSys.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    private final StudentService studentService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    public ResponseEntity<?> create(@RequestBody Student student) {
        studentService.create(student);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/students")
    public List<Student> readAll(){
        return studentService.readAll();
    }

    @PutMapping("/students")
    public ResponseEntity<?> update(@RequestBody Student student, int id){
        studentService.update(student, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/students/{id}")
    public ResponseEntity<Student> findById(@PathVariable(name = "id")int id){
        final Student student = studentService.read(id);
        return student!=null?
                new ResponseEntity<>(student, HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
