package com.registationSystem.regSys.rest.impl;

import com.registationSystem.regSys.dto.rq.RqStudentAttendanceDTO;
import com.registationSystem.regSys.exception.CreationException;
import com.registationSystem.regSys.exception.UpdateException;
import com.registationSystem.regSys.rest.StudentAttendanceApi;
import com.registationSystem.regSys.service.impl.StudentAttendanceServiceImpl;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@Getter
public class StudentAttendanceController implements StudentAttendanceApi {

    private final StudentAttendanceServiceImpl studentAttendanceService;

    @Autowired
    public StudentAttendanceController(StudentAttendanceServiceImpl studentAttendanceService) {
        this.studentAttendanceService = studentAttendanceService;
    }

    @Override
    public ResponseEntity<?> create(RqStudentAttendanceDTO rqStudentAttendanceDTO) throws CreationException {
        if (rqStudentAttendanceDTO.getStudentId() == null) {
            throw new CreationException("ID студента не должно быть NULL");
        }
        if (rqStudentAttendanceDTO.getLessonId() == null) {
            throw new CreationException("ID урока не должно быть NULL");
        }
        try {
            studentAttendanceService.create(rqStudentAttendanceDTO);
        } catch (NoSuchElementException ex) {
            throw new CreationException("ID не существует");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(RqStudentAttendanceDTO rqStudentAttendanceDTO, int id) throws UpdateException {
        if (rqStudentAttendanceDTO.getStudentId() == null) {
            throw new UpdateException("ID студента не должно быть NULL");
        }
        if (rqStudentAttendanceDTO.getLessonId() == null) {
            throw new UpdateException("ID урока не должно быть NULL");
        }
        try {
            studentAttendanceService.update(rqStudentAttendanceDTO, id);
        } catch (NoSuchElementException ex) {
            throw new UpdateException("ID не существует");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
