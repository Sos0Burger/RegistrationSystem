package com.registationSystem.regSys.rest.impl;

import com.registationSystem.regSys.dao.GroupDAO;
import com.registationSystem.regSys.dao.StudentDAO;
import com.registationSystem.regSys.dto.rq.RqGroupDTO;
import com.registationSystem.regSys.dto.rq.RqStudentDTO;
import com.registationSystem.regSys.dto.rs.RsGroupDTO;
import com.registationSystem.regSys.dto.rs.RsStudentDTO;
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
        groupService.create(rqGroupDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<RsGroupDTO>> readAll(){
        return new ResponseEntity<>(groupService.readAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(@RequestBody RqGroupDTO rqGroupDTO, int id){
       return groupService.update(rqGroupDTO, id);
    }

    @Override
    public ResponseEntity<RsGroupDTO> findById(@PathVariable(name = "id")int id){
        final GroupDAO groupDAO = groupService.read(id);
        return groupDAO !=null?
                new ResponseEntity<>(Mapper.groupDAOToGroupDTO(groupDAO) , HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @Override
    public ResponseEntity<?> register(@PathVariable(name = "id")int groupId, @PathVariable(name = "studentId")int studentId) {
        return groupService.registration(groupId, studentId);
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

    @Override
    public ResponseEntity<List<RsStudentDTO>> getStudents(int groupId) {
        return  new ResponseEntity<>(groupService.getStudents(groupId),HttpStatus.OK);
    }
}
