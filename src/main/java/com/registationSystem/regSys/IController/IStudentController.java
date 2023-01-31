package com.registationSystem.regSys.IController;

import com.registationSystem.regSys.Entities.Lesson;
import com.registationSystem.regSys.Entities.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


public interface IStudentController {
    @PostMapping("/students")
    ResponseEntity<?> create(@RequestBody Student student);
    @GetMapping("/students")
    List<Student> readAll();
    @GetMapping("/students/{id}/schedule")
    Set<Lesson> getSchedule(@PathVariable(name = "id")int id);
    @GetMapping("/students/{id}")
    ResponseEntity<Student> findById(@PathVariable(name = "id")int id);

    @PutMapping("/students")
    ResponseEntity<?> update(@RequestBody Student student, int id);

    @DeleteMapping("/students/{id}")
    ResponseEntity<?> stopStudying(@PathVariable(name = "id")int id);


}
