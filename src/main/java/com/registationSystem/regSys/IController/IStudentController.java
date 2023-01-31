package com.registationSystem.regSys.IController;

import com.registationSystem.regSys.Entities.Lesson;
import com.registationSystem.regSys.Entities.Student;
import com.registationSystem.regSys.Models.StudentModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


public interface IStudentController {
    @PostMapping()
    ResponseEntity<?> create(@RequestBody StudentModel studentModel);
    @GetMapping()
    List<Student> readAll();
    @GetMapping("/{id}/schedule")
    Set<Lesson> getSchedule(@PathVariable(name = "id")int id);
    @GetMapping("/{id}")
    ResponseEntity<Student> findById(@PathVariable(name = "id")int id);

    @PutMapping("")
    ResponseEntity<?> update(@RequestBody Student student, int id);

    @DeleteMapping("/{id}")
    ResponseEntity<?> stopStudying(@PathVariable(name = "id")int id);


}
