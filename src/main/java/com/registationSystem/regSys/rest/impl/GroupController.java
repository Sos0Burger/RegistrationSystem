package com.registationSystem.regSys.rest.impl;

import com.registationSystem.regSys.dto.rq.RqGroupDTO;
import com.registationSystem.regSys.dto.rs.RsGroupDTO;
import com.registationSystem.regSys.dto.rs.RsStudentDTO;
import com.registationSystem.regSys.exception.DeleteException;
import com.registationSystem.regSys.exception.FindException;
import com.registationSystem.regSys.exception.UpdateException;
import com.registationSystem.regSys.mapper.Mapper;
import com.registationSystem.regSys.rest.GroupApi;
import com.registationSystem.regSys.service.GroupService;
import com.registationSystem.regSys.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class GroupController implements GroupApi {
    private final GroupService groupService;
    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;

    }

    @Override
    public ResponseEntity<?> create(@RequestBody RqGroupDTO rqGroupDTO) {
        groupService.create(rqGroupDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<RsGroupDTO>> readAll() {
        return new ResponseEntity<>(groupService.readAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(@RequestBody RqGroupDTO rqGroupDTO, int id) throws UpdateException {
        try {
            return groupService.update(rqGroupDTO, id);
        } catch (NoSuchElementException ex) {
            throw new UpdateException("ID не существует");
        }
    }

    @Override
    public ResponseEntity<RsGroupDTO> findById(@PathVariable(name = "id") int id) throws FindException {
        try {
            return new ResponseEntity<>(Mapper.groupDAOToGroupDTO(groupService.read(id)), HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            throw new FindException("ID не существует");
        }
    }

    @Override
    public ResponseEntity<?> register(@PathVariable(name = "id") int groupId, @PathVariable(name = "studentId") int studentId) throws FindException {
        try {
            return groupService.registration(groupId, studentId);
        } catch (NoSuchElementException ex) {
            throw new FindException("ID не существует");
        }
    }

    @Override
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) throws DeleteException {
        try {
            groupService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            throw new DeleteException("ID не существует");
        }
    }

    @Override
    public ResponseEntity<List<RsStudentDTO>> getStudents(int groupId) throws FindException {
        try {
            return new ResponseEntity<>(groupService.getStudents(groupId), HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            throw new FindException("ID не существует");
        }
    }
}
