package com.registationSystem.regSys.controller.controllerImpl;

import com.registationSystem.regSys.dao.LessonDAO;
import com.registationSystem.regSys.dao.StudentDAO;
import com.registationSystem.regSys.dto.rs.RsLessonDTO;
import com.registationSystem.regSys.dto.rs.RsStudentDTO;
import com.registationSystem.regSys.Converter;
import com.registationSystem.regSys.Services.GroupService;
import com.registationSystem.regSys.Services.StudentService;
import com.registationSystem.regSys.controller.StudentController;
import lombok.Getter;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/students")
@Getter
public class StudentsControllerImpl implements StudentController {
    private final StudentService studentService;
    private final GroupService groupService;
    @Autowired
    public StudentsControllerImpl(StudentService studentService, GroupService groupService) {
        this.studentService = studentService;
        this.groupService = groupService;
    }

    @Override
    public Response create(@RequestBody RsStudentDTO rsStudentDTO) {

        studentService.create(Converter.studentDTOToStudentDAO(rsStudentDTO, this));
        return new Response(HttpStatus.OK.value());
    }

   @Override
    public ResponseEntity<List<RsStudentDTO>> readAll(){
        List<RsStudentDTO> rsStudentDTOS = new ArrayList<>();
       for (StudentDAO studentDAO :studentService.readAll()
            ) {
           rsStudentDTOS.add(Converter.studentEntityToStudentModel(studentDAO));
       }
       return new ResponseEntity<>(rsStudentDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(@RequestBody RsStudentDTO rsStudentDTO){
        StudentDAO studentDAO = studentService.read(rsStudentDTO.getId());
        if(studentDAO !=null){
            studentDAO.setAge(rsStudentDTO.getAge());
            studentDAO.setName(rsStudentDTO.getFirstName());
            studentDAO.setSurname(rsStudentDTO.getSurname());
            studentDAO.setGroupDAO(groupService.read(rsStudentDTO.getGroupId()));
            studentService.update(studentDAO, studentDAO.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public ResponseEntity<RsStudentDTO> findById(@PathVariable(name = "id")int id){
        final StudentDAO studentDAO = studentService.read(id);
        return studentDAO !=null?
                new ResponseEntity<>(Converter.studentEntityToStudentModel(studentDAO), HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
        lessonDAOList.removeIf(LessonDAO::isDone);
        for (LessonDAO lessonDAO : lessonDAOList
             ) {
            rsLessonDTOS.add(Converter.lessonEntityToLessonModel(lessonDAO));
        }
        return new ResponseEntity<>(rsLessonDTOS, HttpStatus.OK);
    }
}
