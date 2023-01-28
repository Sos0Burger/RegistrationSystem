package com.registationSystem.regSys.Controllers;

import com.registationSystem.regSys.Models.Group;
import com.registationSystem.regSys.Models.Student;
import com.registationSystem.regSys.Services.GroupService;
import com.registationSystem.regSys.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class GroupsController {
    private final GroupService groupService;
    private final StudentService studentService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    public GroupsController(GroupService groupService, StudentService studentService) {
        this.groupService = groupService;
        this.studentService = studentService;
    }

    @PostMapping("/groups")
    public ResponseEntity<?> create(@RequestBody Group group) {
        groupService.create(group);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/groups")
    public List<Group> readAll(){
        return groupService.readAll();
    }

    @PutMapping("/groups")
    public ResponseEntity<?> update(@RequestBody Group group, int id){
        groupService.update(group, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/groups/{id}")
    public ResponseEntity<Group> findById(@PathVariable(name = "id")int id){
        final Group group = groupService.read(id);
        return group!=null?
                new ResponseEntity<>(group, HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/groups/{id}/{studentId}")
    public ResponseEntity<?> register(@PathVariable(name = "id")int id, @PathVariable(name = "studentId")int studentId){
        final Group group = groupService.read(id);
        final Student student = studentService.read(studentId);
        if(student.getAge()> group.getMaxAge()|| student.getAge()< group.getMinAge()){
            //вот это поменять можно на ошибку какую-нибудь
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Age is not acceptable");
        }
        else {
            group.setStudentCounter(group.getStudentCounter() + 1);
            student.setGroupId(id);
            student.setStudying(true);
            studentService.update(student, studentId);
            return new ResponseEntity<>(HttpStatus.OK);

        }
    }
    @DeleteMapping("/groups/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id")int id){
        final Group group = groupService.read(id);
        if (group!=null){
            groupService.delete(group.getId());

            //Изменяем значения атрибутов студентов,которые находились в группе
            if(group.getStudentCounter()!=0){
                List<Student> groupStudents = studentService.findByGroupId(group.getId());
                for (Student student: groupStudents
                     ) {
                    student.setStudying(false);
                    student.setGroupId(-1);
                    studentService.update(student, student.getId());
                }
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
