package com.registationSystem.regSys.Controllers;

import com.registationSystem.regSys.IController.IGroupsController;
import com.registationSystem.regSys.Entities.Group;
import com.registationSystem.regSys.Entities.Student;
import com.registationSystem.regSys.Models.GroupModel;
import com.registationSystem.regSys.Services.GroupService;
import com.registationSystem.regSys.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GroupsController implements IGroupsController {
    private final GroupService groupService;
    private final StudentService studentService;


    @Autowired
    public GroupsController(GroupService groupService, StudentService studentService) {
        this.groupService = groupService;
        this.studentService = studentService;
    }

    @Override
    public ResponseEntity<?> create(@RequestBody Group group) {
        groupService.create(group);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public List<Group> readAll(){
        return groupService.readAll();
    }

    @Override
    public ResponseEntity<?> update(@RequestBody Group group, int id){
        groupService.update(group, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Override
    public ResponseEntity<Group> findById(@PathVariable(name = "id")int id){
        final Group group = groupService.read(id);
        return group!=null?
                new ResponseEntity<>(group, HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @Override
    public ResponseEntity<?> register(@PathVariable(name = "id")int id, @PathVariable(name = "studentId")int studentId) {
        Group group = groupService.read(id);
        Student student = studentService.read(studentId);
        if(!(group.getStudentsList().size()+1<=group.getSize())){
            if(!(student.getAge()>= group.getMinAge() && student.getAge()<=group.getMaxAge())){
                return new ResponseEntity<>("Age is not suitable",HttpStatus.NOT_ACCEPTABLE);
            }
            return new ResponseEntity<>("Group is full", HttpStatus.NOT_ACCEPTABLE);

        }
        if(group.getStudentsList().add(student)){
            groupService.update(group, group.getId());
            student.setGroup(group);
            studentService.update(student, student.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Student already exist in this group", HttpStatus.CONFLICT);
        }
    }
    @Override
    public ResponseEntity<?> delete(@PathVariable(name = "id")int id){
       Group group = groupService.read(id);
        for (Student student : group.getStudentsList()) {
            student.setGroup(null);
        }
        groupService.delete(group.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
