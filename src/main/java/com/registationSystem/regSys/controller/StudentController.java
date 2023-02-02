package com.registationSystem.regSys.controller;

import com.registationSystem.regSys.dto.rs.RsLessonDTO;
import com.registationSystem.regSys.dto.rs.RsStudentDTO;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public interface StudentController {
    @PostMapping()
    Response create(@RequestBody RsStudentDTO rsStudentDTO);
    @GetMapping()
    ResponseEntity<List<RsStudentDTO>> readAll();
    @GetMapping("/{id}/schedule")
    ResponseEntity<List<RsLessonDTO>> getSchedule(@PathVariable(name = "id")int id);
    @GetMapping("/{id}")
    ResponseEntity<RsStudentDTO> findById(@PathVariable(name = "id")int id);

    @PutMapping("")
    ResponseEntity<?> update(@RequestBody RsStudentDTO rsStudentDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<?> stopStudying(@PathVariable(name = "id")int id);
}
