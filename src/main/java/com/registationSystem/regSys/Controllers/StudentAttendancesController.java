package com.registationSystem.regSys.Controllers;

import com.registationSystem.regSys.IController.IStudentAttendanceController;
import com.registationSystem.regSys.Entities.StudentAttendance;
import com.registationSystem.regSys.Models.StudentAttendanceModel;
import com.registationSystem.regSys.Parser;
import com.registationSystem.regSys.Services.LessonService;
import com.registationSystem.regSys.Services.StudentAttendanceService;
import com.registationSystem.regSys.Services.StudentService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/studentAttendances")
@Getter
public class StudentAttendancesController implements IStudentAttendanceController {

    private final StudentAttendanceService studentAttendanceService;
    private final StudentService studentService;
    private final LessonService lessonService;

    @Autowired
    public StudentAttendancesController(StudentAttendanceService studentAttendanceService, StudentService studentService, LessonService lessonService){
        this.studentAttendanceService = studentAttendanceService;
        this.studentService = studentService;
        this.lessonService = lessonService;
    }

    @Override
    public ResponseEntity<?> create(StudentAttendanceModel studentAttendanceModel) {
        if(studentAttendanceService.read(studentAttendanceModel.getId())==null){
            studentAttendanceService.create(Parser.studentAttendanceModelToStudentAttendanceEntity(studentAttendanceModel,this));
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else {
            return update(studentAttendanceModel);

        }
    }

    @Override
    public ResponseEntity<?> update(StudentAttendanceModel studentAttendanceModel) {
        studentAttendanceService.update(
                Parser.studentAttendanceModelToStudentAttendanceEntity(studentAttendanceModel, this),
                studentAttendanceModel.getId()
        );
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
