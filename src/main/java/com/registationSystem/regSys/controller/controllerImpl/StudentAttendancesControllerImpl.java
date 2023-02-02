package com.registationSystem.regSys.controller.controllerImpl;

import com.registationSystem.regSys.dto.rs.RsStudentAttendanceDTO;
import com.registationSystem.regSys.Converter;
import com.registationSystem.regSys.Services.LessonService;
import com.registationSystem.regSys.Services.StudentAttendanceService;
import com.registationSystem.regSys.Services.StudentService;
import com.registationSystem.regSys.controller.StudentAttendanceController;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/studentAttendances")
@Getter
public class StudentAttendancesControllerImpl implements StudentAttendanceController {

    private final StudentAttendanceService studentAttendanceService;
    private final StudentService studentService;
    private final LessonService lessonService;

    @Autowired
    public StudentAttendancesControllerImpl(StudentAttendanceService studentAttendanceService, StudentService studentService, LessonService lessonService){
        this.studentAttendanceService = studentAttendanceService;
        this.studentService = studentService;
        this.lessonService = lessonService;
    }

    @Override
    public ResponseEntity<?> create(RsStudentAttendanceDTO rsStudentAttendanceDTO) {
        if(studentAttendanceService.read(rsStudentAttendanceDTO.getId())==null){
            studentAttendanceService.create(Converter.studentAttendanceModelToStudentAttendanceEntity(rsStudentAttendanceDTO,this));
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else {
            return update(rsStudentAttendanceDTO);

        }
    }

    @Override
    public ResponseEntity<?> update(RsStudentAttendanceDTO rsStudentAttendanceDTO) {
        studentAttendanceService.update(
                Converter.studentAttendanceModelToStudentAttendanceEntity(rsStudentAttendanceDTO, this),
                rsStudentAttendanceDTO.getId()
        );
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
