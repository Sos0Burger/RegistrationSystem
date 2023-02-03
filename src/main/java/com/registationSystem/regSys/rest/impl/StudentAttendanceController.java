package com.registationSystem.regSys.rest.impl;

import com.registationSystem.regSys.dto.rq.RqStudentAttendanceDTO;
import com.registationSystem.regSys.rest.StudentAttendanceApi;
import com.registationSystem.regSys.service.impl.LessonServiceImpl;
import com.registationSystem.regSys.service.impl.StudentAttendanceServiceImpl;
import com.registationSystem.regSys.service.impl.StudentServiceImpl;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Getter
public class StudentAttendanceController implements StudentAttendanceApi {

    private final StudentAttendanceServiceImpl studentAttendanceService;

    @Autowired
    public StudentAttendanceController(StudentAttendanceServiceImpl studentAttendanceService){
        this.studentAttendanceService = studentAttendanceService;
    }

    @Override
    public ResponseEntity<?> create(RqStudentAttendanceDTO rqStudentAttendanceDTO) {
      studentAttendanceService.create(rqStudentAttendanceDTO);
      return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(RqStudentAttendanceDTO rqStudentAttendanceDTO, int id) {
        studentAttendanceService.update(rqStudentAttendanceDTO, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
