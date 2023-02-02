package com.registationSystem.regSys.rest.impl;

import com.registationSystem.regSys.dto.rq.RqStudentAttendanceDTO;
import com.registationSystem.regSys.dto.rs.RsStudentAttendanceDTO;
import com.registationSystem.regSys.mapper.Mapper;
import com.registationSystem.regSys.service.impl.LessonServiceImpl;
import com.registationSystem.regSys.service.impl.StudentAttendanceServiceImpl;
import com.registationSystem.regSys.service.impl.StudentServiceImpl;
import com.registationSystem.regSys.rest.StudentAttendanceApi;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/studentAttendances")
@Getter
public class StudentAttendanceController implements StudentAttendanceApi {

    private final StudentAttendanceServiceImpl studentAttendanceService;
    private final StudentServiceImpl studentServiceImpl;
    private final LessonServiceImpl lessonService;

    @Autowired
    public StudentAttendanceController(StudentAttendanceServiceImpl studentAttendanceService, StudentServiceImpl studentServiceImpl, LessonServiceImpl lessonService){
        this.studentAttendanceService = studentAttendanceService;
        this.studentServiceImpl = studentServiceImpl;
        this.lessonService = lessonService;
    }

    @Override
    public ResponseEntity<?> create(RqStudentAttendanceDTO rqStudentAttendanceDTO) {
        if(studentAttendanceService.read(rqStudentAttendanceDTO.getId())==null){
            studentAttendanceService.create(Mapper.studentAttendanceDTOToStudentAttendanceDAO(rqStudentAttendanceDTO));
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else {
            return update(rqStudentAttendanceDTO, rqStudentAttendanceDTO.getStudentId());

        }
    }

    @Override
    public ResponseEntity<?> update(RqStudentAttendanceDTO rqStudentAttendanceDTO, int id) {
        studentAttendanceService.update(
                Mapper.studentAttendanceDTOToStudentAttendanceDAO(rqStudentAttendanceDTO),
                id
        );
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
