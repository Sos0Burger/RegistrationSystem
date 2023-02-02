package com.registationSystem.regSys.rest;

import com.registationSystem.regSys.dto.rq.RqStudentAttendanceDTO;
import com.registationSystem.regSys.dto.rs.RsStudentAttendanceDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


public interface StudentAttendanceApi {
    @PostMapping
    ResponseEntity<?> create(@RequestBody RqStudentAttendanceDTO rqStudentAttendanceDTO);
    @PutMapping("/{id}")
    ResponseEntity<?> update(@RequestBody RqStudentAttendanceDTO rqStudentAttendanceDTO, @PathVariable int id);
}
