package com.registationSystem.regSys.ControllerInterInterfaces;

import com.registationSystem.regSys.Models.StudentAttendance;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public interface IStudentAttendanceController {
    @PostMapping("/studentAttendance")
    ResponseEntity<?> create(@RequestBody List<StudentAttendance> studentAttendanceList);

}
