package com.registationSystem.regSys.Controllers;

import com.registationSystem.regSys.Models.Coach;
import com.registationSystem.regSys.Models.Group;
import com.registationSystem.regSys.Models.Lesson;
import com.registationSystem.regSys.Services.CoachService;
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
public class CoachesController {
    private final CoachService coachService;
    private final LessonService lessonService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    public CoachesController(CoachService coachService, LessonService lessonService) {
        this.coachService = coachService;
        this.lessonService = lessonService;
    }

    @PostMapping("/coaches")
    public ResponseEntity<?> create(@RequestBody Coach coach) {
        coachService.create(coach);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/coaches")
    public List<Coach> readAll(){
        return coachService.readAll();
    }

    @GetMapping("/coaches/{id}/unfinishedLessons")
    public List<Lesson> getUnfinishedLessons(@RequestParam(name="id")int id){
        return lessonService.findByCoachId(id,false);
    }
    @GetMapping("/coaches/{id}/finishedLessons")
    public List<Lesson> getFinishedLessons(@RequestParam(name="id")int id){
        return lessonService.findByCoachId(id,true);
    }
    @PutMapping("/coaches/{id}/studentCheck")
    public ResponseEntity<?> studentCheck(@RequestParam(name="id")int id, @RequestBody Lesson lesson, @RequestBody int[] absenceList){
        if(lesson.getAbsenceList()!=null) {
            //вот тут возможно можно полегче
            int[] newAbsenceList = new int[absenceList.length + 1];
            for (int i : absenceList
            ) {
                newAbsenceList[i] = absenceList[i];
            }
            newAbsenceList[newAbsenceList.length - 1] = id;
            lesson.setAbsenceList(newAbsenceList);
        }
        else{
            lesson.setAbsenceList(absenceList);
        }
        lessonService.update(lesson, lesson.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
