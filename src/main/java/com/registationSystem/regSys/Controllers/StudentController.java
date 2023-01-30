package com.registationSystem.regSys.Controllers;

import com.registationSystem.regSys.Models.Group;
import com.registationSystem.regSys.Models.Lesson;
import com.registationSystem.regSys.Models.Student;
import com.registationSystem.regSys.Services.GroupService;
import com.registationSystem.regSys.Services.LessonService;
import com.registationSystem.regSys.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    private final StudentService studentService;
    private final GroupService groupService;
    private final LessonService lessonService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentController(StudentService studentService, GroupService groupService, LessonService lessonService) {
        this.studentService = studentService;
        this.groupService = groupService;
        this.lessonService = lessonService;
    }

    @PostMapping("/students")
    public ResponseEntity<?> create(@RequestBody Student student) {
        studentService.create(student);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/students")
    public List<Student> readAll(){
        return studentService.readAll();
    }

    @PutMapping("/students")
    public ResponseEntity<?> update(@RequestBody Student student, int id){
        studentService.update(student, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/students/{id}")
    public ResponseEntity<Student> findById(@PathVariable(name = "id")int id){
        final Student student = studentService.read(id);
        return student!=null?
                new ResponseEntity<>(student, HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id")int id){
        final Student student = studentService.read(id);
        if(student!=null){
            //удаляем студента из таблицы
            studentService.delete(student.getId());
            //Уменьшаем количество человек в группе, если он состоял в ней
            if(student.isStudying()){
                final Group group = groupService.read(student.getGroupId());
                group.setStudentCounter(group.getStudentCounter()-1);
                groupService.update(group, student.getGroupId());
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @GetMapping("/students/{id}/schedule")
    public ResponseEntity<List<Lesson>> getSchedule(@PathVariable(name = "id")int id) {
        final List<Lesson> lessonList = lessonService.findByGroupId(studentService.read(id).getGroupId());
        return lessonList!=null?
                new ResponseEntity<>(lessonList, HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
