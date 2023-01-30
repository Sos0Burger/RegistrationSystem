package com.registationSystem.regSys.ControllerInterInterfaces;

import com.registationSystem.regSys.Models.Lesson;
import com.registationSystem.regSys.Models.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public interface IStudentController {
    @GetMapping("/students")
    List<Student> readAll();
    @PostMapping("/students")
    ResponseEntity<?> create(@RequestBody Student student);
    @PutMapping("/students")
    ResponseEntity<?> update(@RequestBody Student student, int id);
    @GetMapping("/students/{id}")
    ResponseEntity<Student> findById(@PathVariable(name = "id")int id);

    @DeleteMapping("/students/{id}")
    ResponseEntity<?> delete(@PathVariable(name = "id")int id);
    @GetMapping("/students/{id}/schedule")
    ResponseEntity<List<Lesson>> getSchedule(@PathVariable(name = "id")int id);
}
