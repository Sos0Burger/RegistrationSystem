package com.registationSystem.regSys.rest.impl;

import com.registationSystem.regSys.dao.LessonDAO;
import com.registationSystem.regSys.dao.StudentDAO;
import com.registationSystem.regSys.dto.rq.RqStudentDTO;
import com.registationSystem.regSys.dto.rs.RsLessonDTO;
import com.registationSystem.regSys.dto.rs.RsStudentDTO;
import com.registationSystem.regSys.exception.ControllerException;
import com.registationSystem.regSys.mapper.Mapper;
import com.registationSystem.regSys.service.StudentService;
import com.registationSystem.regSys.service.impl.GroupServiceImpl;
import com.registationSystem.regSys.rest.StudentApi;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@RestController
@Getter
public class StudentController implements StudentApi {
    private final StudentService studentService;
    private final GroupServiceImpl groupService;

    @Autowired
    public StudentController(StudentService studentService, GroupServiceImpl groupService) {
        this.studentService = studentService;
        this.groupService = groupService;
    }

    @Override
    public ResponseEntity<Void> create(RqStudentDTO rqStudentDTO) throws ControllerException {
        try {
            studentService.create(rqStudentDTO);
        }
        catch (NoSuchElementException ex){
            throw new ControllerException(ex.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

   @Override
    public ResponseEntity<List<RsStudentDTO>> readAll(){
        List<RsStudentDTO> rsStudentDTOS = new ArrayList<>();
       for (StudentDAO studentDAO : studentService.readAll()
            ) {
           rsStudentDTOS.add(Mapper.studentDAOToStudentDTO(studentDAO));
       }
       return new ResponseEntity<>(rsStudentDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(RqStudentDTO rqStudentDTO, int id) throws ControllerException {
        try {
            studentService.update(rqStudentDTO, id);
        }
        catch (NoSuchElementException ex){
            throw new ControllerException(ex.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Override
    public ResponseEntity<RsStudentDTO> findById(@PathVariable(name = "id")int id){
        return new ResponseEntity<>(Mapper.studentDAOToStudentDTO(studentService.read(id)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> stopStudying(@PathVariable(name = "id")int id){
        StudentDAO studentDAO = studentService.read(id);
        studentDAO.setGroupDAO(null);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Override
    public ResponseEntity<List<RsLessonDTO>> getSchedule(@PathVariable(name = "id")int id) {
        List<RsLessonDTO> rsLessonDTOS = new ArrayList<>();
        Set<LessonDAO> lessonDAOList = groupService.read(studentService.read(id).getGroupDAO().getId()).getLessonsList();
        lessonDAOList.removeIf(LessonDAO::getIsDone);
        for (LessonDAO lessonDAO : lessonDAOList
             ) {
            rsLessonDTOS.add(Mapper.lessonDAOToLessonDTO(lessonDAO));
        }
        return new ResponseEntity<>(rsLessonDTOS, HttpStatus.OK);
    }
}
