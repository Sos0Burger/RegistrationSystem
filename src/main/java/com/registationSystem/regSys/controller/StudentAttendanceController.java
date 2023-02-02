package com.registationSystem.regSys.controller;

import com.registationSystem.regSys.dto.rs.RsStudentAttendanceDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


public interface StudentAttendanceController {
    @PostMapping("")
    ResponseEntity<?> create(@RequestBody RsStudentAttendanceDTO rsStudentAttendanceDTO);
    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody RsStudentAttendanceDTO rsStudentAttendanceDTO);
}
