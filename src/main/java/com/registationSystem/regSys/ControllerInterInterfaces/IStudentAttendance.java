package com.registationSystem.regSys.ControllerInterInterfaces;

import com.registationSystem.regSys.Models.StudentAttendance;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public interface IStudentAttendance {
    @PostMapping("/studentAttendance")
    ResponseEntity<?> create(@RequestBody List<StudentAttendance> studentAttendanceList);

    @GetMapping("/studenAttendance/{id]")
    List<StudentAttendance> getAttendanceByStudentId(@PathVariable(name="id")int id);
}
