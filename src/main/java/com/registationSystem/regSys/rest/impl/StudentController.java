package com.registationSystem.regSys.rest.impl;

import com.registationSystem.regSys.dto.rq.RqStudentDTO;
import com.registationSystem.regSys.dto.rs.RsLessonDTO;
import com.registationSystem.regSys.dto.rs.RsStudentDTO;
import com.registationSystem.regSys.exception.CreationException;
import com.registationSystem.regSys.exception.FindException;
import com.registationSystem.regSys.exception.RegistrationException;
import com.registationSystem.regSys.exception.UpdateException;
import com.registationSystem.regSys.mapper.Mapper;
import com.registationSystem.regSys.rest.StudentApi;
import com.registationSystem.regSys.service.StudentService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Getter
public class StudentController implements StudentApi {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public ResponseEntity<Void> create(RqStudentDTO rqStudentDTO) throws CreationException, RegistrationException {
        try {
            studentService.create(rqStudentDTO);
        } catch (NoSuchElementException ex) {
            throw new CreationException("ID не существует");
        } catch (RegistrationException ex){
            throw new RegistrationException(ex.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @Override
    public ResponseEntity<?> update(RqStudentDTO rqStudentDTO, int id) throws UpdateException {
        try {
            studentService.update(rqStudentDTO, id);
        } catch (NoSuchElementException ex) {
            throw new UpdateException("ID не существует");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<RsStudentDTO>> findAll() {
        return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RsStudentDTO> find(@PathVariable(name = "id") int id) throws FindException {
        try {
            return new ResponseEntity<>(Mapper.studentDAOToStudentDTO(studentService.find(id)), HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            throw new FindException("ID не существует");
        }
    }

    @Override
    public ResponseEntity<List<RsLessonDTO>> getSchedule(@PathVariable(name = "id") int id) throws FindException {
        try {
            return new ResponseEntity<>(studentService.getScheduleById(id), HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            throw new FindException("Студент с таким ID не существует");
        }
    }
}
