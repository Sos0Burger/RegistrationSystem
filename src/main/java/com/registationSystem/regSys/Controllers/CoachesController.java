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

    @GetMapping("/coaches/{id}/shedule")
    public List<Lesson> getShedule(@RequestParam(name="id")int id){
        return lessonService.findByCoachId(id);
    }

}
