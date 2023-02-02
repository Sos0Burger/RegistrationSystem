package com.registationSystem.regSys.rest.impl;

import com.registationSystem.regSys.dao.GroupDAO;
import com.registationSystem.regSys.dao.StudentDAO;
import com.registationSystem.regSys.dto.rq.RqGroupDTO;
import com.registationSystem.regSys.dto.rq.RqStudentDTO;
import com.registationSystem.regSys.dto.rs.RsGroupDTO;
import com.registationSystem.regSys.mapper.Mapper;
import com.registationSystem.regSys.rest.GroupApi;
import com.registationSystem.regSys.service.GroupService;
import com.registationSystem.regSys.service.StudentService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Getter
public class GroupController implements GroupApi {
    private final GroupService groupService;
    private final StudentService studentService;

    @Autowired
    public GroupController(GroupService groupService, StudentService studentService) {
        this.groupService = groupService;
        this.studentService = studentService;
    }

    @Override
    public ResponseEntity<?> create(@RequestBody RqGroupDTO rqGroupDTO) {
        groupService.create(Mapper.groupDTOToGroupDAO(rqGroupDTO));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<RsGroupDTO>> readAll(){
        List<RsGroupDTO> rsGroupDTOS = new ArrayList<>();
        for (GroupDAO groupDAO :groupService.readAll()
        ) {
            rsGroupDTOS.add(Mapper.groupDAOToGroupDTO(groupDAO));
        }
        return new ResponseEntity<>(rsGroupDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(@RequestBody RqGroupDTO rqGroupDTO, int id){
        GroupDAO groupDAO = groupService.read(id);
        if(groupDAO !=null){
           groupDAO.setSize(rqGroupDTO.getSize());
           groupDAO.setMinAge(rqGroupDTO.getMinAge());
           groupDAO.setMaxAge(rqGroupDTO.getMaxAge());
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
                new ResponseEntity<>(Mapper.groupDAOToGroupDTO(groupDAO) , HttpStatus.OK):
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
            RqStudentDTO rqStudentDTO = new RqStudentDTO(studentDAO.getName(), studentDAO.getSurname(), studentDAO.getAge(), studentDAO.getGroupDAO().getId());
            studentService.update(rqStudentDTO, studentId);
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
