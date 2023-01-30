package com.registationSystem.regSys.Controllers;

import com.registationSystem.regSys.ControllerInterInterfaces.IStudentAttendance;
import com.registationSystem.regSys.Models.StudentAttendance;
import com.registationSystem.regSys.Services.StudentAttendanceService;
import com.registationSystem.regSys.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentAttendanceController implements IStudentAttendance {

    private final StudentAttendanceService studentAttendanceService;

    @Autowired
    StudentAttendanceController(StudentAttendanceService studentAttendanceService){
        this.studentAttendanceService = studentAttendanceService;
    }

    @Override
    public ResponseEntity<?> create(List<StudentAttendance> studentAttendanceList) {
        studentAttendanceService.create(studentAttendanceList);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public List<StudentAttendance> getStudentAttendanceById(int id) {
        return studentAttendanceService.getByStudentId(id);
    }
}
