package com.registationSystem.regSys.Controllers;

import com.registationSystem.regSys.ControllerInterInterfaces.ICoachesController;
import com.registationSystem.regSys.Models.Coach;
import com.registationSystem.regSys.Models.Lesson;
import com.registationSystem.regSys.Services.CoachService;
import com.registationSystem.regSys.Services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CoachesController implements ICoachesController {
    private final CoachService coachService;
    private final LessonService lessonService;

    @Autowired
    public CoachesController(CoachService coachService, LessonService lessonService) {
        this.coachService = coachService;
        this.lessonService = lessonService;
    }

    @Override
    public ResponseEntity<?> create(@RequestBody Coach coach) {
        coachService.create(coach);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @Override
    public List<Coach> readAll(){
        return coachService.readAll();
    }

    @Override
    public List<Lesson> getUnfinishedLessons(@RequestParam(name="id")int id){
        return lessonService.findByCoachId(id,false);
    }
    @Override
    public List<Lesson> getFinishedLessons(@RequestParam(name="id")int id){
        return lessonService.findByCoachId(id,true);
    }
}
