package com.registationSystem.regSys.controller.controllerImpl;

import com.registationSystem.regSys.dao.GroupDAO;
import com.registationSystem.regSys.dao.StudentDAO;
import com.registationSystem.regSys.dto.rs.RsGroupDTO;
import com.registationSystem.regSys.Converter;
import com.registationSystem.regSys.Services.GroupService;
import com.registationSystem.regSys.Services.LessonService;
import com.registationSystem.regSys.Services.StudentService;
import com.registationSystem.regSys.controller.GroupsController;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/groups")
@Getter
public class GroupsControllerImpl implements GroupsController {
    private final GroupService groupService;
    private final StudentService studentService;
    private final LessonService lessonService;


    @Autowired
    public GroupsControllerImpl(GroupService groupService, StudentService studentService, LessonService lessonService) {
        this.groupService = groupService;
        this.studentService = studentService;
        this.lessonService = lessonService;
    }

    @Override
    public ResponseEntity<?> create(@RequestBody RsGroupDTO rsGroupDTO) {
        groupService.create(Converter.groupModelToGroupEntity(rsGroupDTO,this));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<RsGroupDTO>> readAll(){
        List<RsGroupDTO> rsGroupDTOS = new ArrayList<>();
        for (GroupDAO groupDAO :groupService.readAll()
        ) {
            rsGroupDTOS.add(Converter.groupEntityToGroupModel(groupDAO));
        }
        return new ResponseEntity<>(rsGroupDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(@RequestBody RsGroupDTO rsGroupDTO){
        GroupDAO groupDAO = groupService.read(rsGroupDTO.getId());
        if(groupDAO !=null){
           groupDAO.setSize(rsGroupDTO.getSize());
           groupDAO.setMinAge(rsGroupDTO.getMinAge());
           groupDAO.setMaxAge(rsGroupDTO.getMaxAge());
           groupDAO.setStudentsList(groupService.read(rsGroupDTO.getId()).getStudentsList());
           groupDAO.setLessonsList(groupService.read(rsGroupDTO.getId()).getLessonsList());
           groupService.update(groupDAO, groupDAO.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public ResponseEntity<RsGroupDTO> findById(@PathVariable(name = "id")int id){
        final GroupDAO groupDAO = groupService.read(id);
        return groupDAO !=null?
                new ResponseEntity<>(Converter.groupEntityToGroupModel(groupDAO) , HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @Override
    public ResponseEntity<?> register(@PathVariable(name = "id")int id, @PathVariable(name = "studentId")int studentId) {
        GroupDAO groupDAO = groupService.read(id);
        StudentDAO studentDAO = studentService.read(studentId);
        if(!(groupDAO.getStudentsList().size()+1<= groupDAO.getSize())){
            if(!(studentDAO.getAge()>= groupDAO.getMinAge() && studentDAO.getAge()<= groupDAO.getMaxAge())){
                return new ResponseEntity<>("Age is not suitable",HttpStatus.NOT_ACCEPTABLE);
            }
            return new ResponseEntity<>("Group is full", HttpStatus.NOT_ACCEPTABLE);

        }
        if(groupDAO.getStudentsList().add(studentDAO)){
            groupService.update(groupDAO, groupDAO.getId());
            studentDAO.setGroupDAO(groupDAO);
            studentService.update(studentDAO, studentDAO.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Student already exist in this group", HttpStatus.CONFLICT);
        }
    }
    @Override
    public ResponseEntity<?> delete(@PathVariable(name = "id")int id){
       GroupDAO groupDAO = groupService.read(id);
        for (StudentDAO studentDAO : groupDAO.getStudentsList()) {
            studentDAO.setGroupDAO(null);
        }
        groupService.delete(groupDAO.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
