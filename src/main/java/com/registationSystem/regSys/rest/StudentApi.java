package com.registationSystem.regSys.rest;

import com.registationSystem.regSys.dto.rq.RqStudentDTO;
import com.registationSystem.regSys.dto.rs.RsLessonDTO;
import com.registationSystem.regSys.dto.rs.RsStudentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/students")
public interface StudentApi {
    @PostMapping
    ResponseEntity<?> create(@RequestBody RqStudentDTO rqStudentDTO);
    @PutMapping
    ResponseEntity<?> update(@RequestBody RqStudentDTO rqStudentDTO, @PathVariable(name = "id")int id);
    @GetMapping
    ResponseEntity<List<RsStudentDTO>> readAll();
    @GetMapping("/{id}/schedule")
    ResponseEntity<List<RsLessonDTO>> getSchedule(@PathVariable(name = "id")int id);
    @GetMapping("/{id}")
    ResponseEntity<RsStudentDTO> findById(@PathVariable(name = "id")int id);



    @DeleteMapping("/{id}")
    ResponseEntity<?> stopStudying(@PathVariable(name = "id")int id);
}
