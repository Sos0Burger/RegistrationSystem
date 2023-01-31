package com.registationSystem.regSys.IController;

import com.registationSystem.regSys.Entities.StudentAttendance;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


public interface IStudentAttendanceController {
    @PostMapping("/studentAttendance/{lessonId}/{studentId}")
    ResponseEntity<?> create(@RequestBody StudentAttendance studentAttendance,@PathVariable(name = "lessonId")int lessonId, @PathVariable(name = "studentId")int studentId);

}
