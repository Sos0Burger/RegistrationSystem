package com.registationSystem.regSys.Controllers;

import com.registationSystem.regSys.IController.IGroupsController;
import com.registationSystem.regSys.Entities.Group;
import com.registationSystem.regSys.Entities.Student;
import com.registationSystem.regSys.Models.GroupModel;
import com.registationSystem.regSys.Models.StudentModel;
import com.registationSystem.regSys.Parser;
import com.registationSystem.regSys.Services.GroupService;
import com.registationSystem.regSys.Services.LessonService;
import com.registationSystem.regSys.Services.StudentService;
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
public class GroupsController implements IGroupsController {
    private final GroupService groupService;
    private final StudentService studentService;
    private final LessonService lessonService;


    @Autowired
    public GroupsController(GroupService groupService, StudentService studentService,  LessonService lessonService) {
        this.groupService = groupService;
        this.studentService = studentService;
        this.lessonService = lessonService;
    }

    @Override
    public ResponseEntity<?> create(@RequestBody GroupModel groupModel) {
        groupService.create(Parser.groupModelToGroupEntity(groupModel,this));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<GroupModel>> readAll(){
        List<GroupModel> groupModels = new ArrayList<>();
        for (Group group:groupService.readAll()
        ) {
            groupModels.add(Parser.groupEntityToGroupModel(group));
        }
        return new ResponseEntity<>(groupModels, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(@RequestBody GroupModel groupModel){
        Group group = groupService.read(groupModel.getId());
        if(group!=null){
           group.setSize(groupModel.getSize());
           group.setMinAge(groupModel.getMinAge());
           group.setMaxAge(groupModel.getMaxAge());
           group.setStudentsList(groupService.read(groupModel.getId()).getStudentsList());
           group.setLessonsList(groupService.read(groupModel.getId()).getLessonsList());
           groupService.update(group, group.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public ResponseEntity<GroupModel> findById(@PathVariable(name = "id")int id){
        final Group group = groupService.read(id);
        return group!=null?
                new ResponseEntity<>(Parser.groupEntityToGroupModel(group) , HttpStatus.OK):
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
