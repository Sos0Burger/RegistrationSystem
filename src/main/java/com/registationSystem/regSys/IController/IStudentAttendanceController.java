package com.registationSystem.regSys.IController;

import com.registationSystem.regSys.Entities.StudentAttendance;
import com.registationSystem.regSys.Models.StudentAttendanceModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


public interface IStudentAttendanceController {
    @PostMapping("")
    ResponseEntity<?> create(@RequestBody StudentAttendanceModel studentAttendanceModel);
    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody StudentAttendanceModel studentAttendanceModel);
}
