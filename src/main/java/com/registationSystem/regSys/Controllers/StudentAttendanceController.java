package com.registationSystem.regSys.Controllers;

import com.registationSystem.regSys.IController.IStudentAttendanceController;
import com.registationSystem.regSys.Entities.StudentAttendance;
import com.registationSystem.regSys.Services.LessonService;
import com.registationSystem.regSys.Services.StudentAttendanceService;
import com.registationSystem.regSys.Services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class StudentAttendanceController implements IStudentAttendanceController {

    private final StudentAttendanceService studentAttendanceService;
    private final StudentService studentService;
    private final LessonService lessonService;

    @Override
    public ResponseEntity<?> create(StudentAttendance studentAttendance, int lessonId, int studentId) {
        studentAttendance.setStudent(studentService.read(studentId));
        studentAttendance.setLesson(lessonService.read(lessonId));
        studentAttendanceService.create(studentAttendance);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
