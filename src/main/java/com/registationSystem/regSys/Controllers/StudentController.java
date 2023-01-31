package com.registationSystem.regSys.Controllers;

import com.registationSystem.regSys.IController.IStudentController;
import com.registationSystem.regSys.Entities.Lesson;
import com.registationSystem.regSys.Entities.Student;
import com.registationSystem.regSys.Models.StudentModel;
import com.registationSystem.regSys.Parser;
import com.registationSystem.regSys.Services.GroupService;
import com.registationSystem.regSys.Services.StudentService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/students")
@Getter
public class StudentController implements IStudentController {
    private final StudentService studentService;
    private final GroupService groupService;
    @Autowired
    public StudentController(StudentService studentService, GroupService groupService) {
        this.studentService = studentService;
        this.groupService = groupService;
    }

    @Override
    public ResponseEntity<?> create(@RequestBody StudentModel studentModel) {

        studentService.create(Parser.StudentModelToStudentEntity(studentModel, this));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

   @Override
    public List<Student> readAll(){
        return studentService.readAll();
    }

    @Override
    public ResponseEntity<?> update(@RequestBody Student student, int id){
        studentService.update(student, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Override
    public ResponseEntity<Student> findById(@PathVariable(name = "id")int id){
        final Student student = studentService.read(id);
        return student!=null?
                new ResponseEntity<>(student, HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> stopStudying(@PathVariable(name = "id")int id){
        Student student = studentService.read(id);
        student.setGroup(null);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Override
    public Set<Lesson> getSchedule(@PathVariable(name = "id")int id) {
        Set<Lesson> lessonList = groupService.read(studentService.read(id).getGroup().getId()).getLessonsList();
        lessonList.removeIf(Lesson::isDone);
        return lessonList;
    }
}
