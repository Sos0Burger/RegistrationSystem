package com.registationSystem.regSys.Controllers;

import com.registationSystem.regSys.IController.ICoachesController;
import com.registationSystem.regSys.Entities.Coach;
import com.registationSystem.regSys.Entities.Lesson;
import com.registationSystem.regSys.Models.CoachModel;
import com.registationSystem.regSys.Models.GroupModel;
import com.registationSystem.regSys.Models.LessonModel;
import com.registationSystem.regSys.Parser;
import com.registationSystem.regSys.Services.CoachService;
import com.registationSystem.regSys.Services.LessonService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RequestMapping("/coaches")
@RestController
@Getter
public class CoachesController implements ICoachesController {
    private final CoachService coachService;
    private final LessonService lessonService;

    @Autowired
    public CoachesController(CoachService coachService, LessonService lessonService) {
        this.coachService = coachService;
        this.lessonService = lessonService;
    }

    @Override
    public ResponseEntity<?> create(@RequestBody CoachModel coachModel) {
        coachService.create(Parser.coachModelToCoachEntity(coachModel,this));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @Override
    public ResponseEntity<List<CoachModel>> readAll(){
        List<CoachModel> coachModels = new ArrayList<>();
        for (Coach coach : coachService.readAll()
                ) {
            coachModels.add(Parser.coachToCoachModel(coach));
        }
        return new ResponseEntity<>(coachModels, HttpStatus.OK);
    }

    @Override
    public  ResponseEntity<List<LessonModel>> getUnfinishedLessons(@RequestParam(name="id")int id){
        List<LessonModel> lessonModels = new ArrayList<>();
        Set<Lesson> lessonSet = coachService.read(id).getLessonList();
        lessonSet.removeIf(Lesson::isDone);
        for (Lesson lesson:lessonSet
             ) {
            lessonModels.add(Parser.lessonEntityToLessonModel(lesson));
        }
        return new ResponseEntity<>(lessonModels, HttpStatus.OK);
    }
    @Override
    public ResponseEntity<List<LessonModel>> getFinishedLessons(@RequestParam(name="id")int id){
        List<LessonModel> lessonModels = new ArrayList<>();
        Set<Lesson> lessonSet = coachService.read(id).getLessonList();
        lessonSet.removeIf(lesson -> !lesson.isDone());
        for (Lesson lesson:lessonSet
        ) {
            lessonModels.add(Parser.lessonEntityToLessonModel(lesson));
        }
        return new ResponseEntity<>(lessonModels, HttpStatus.OK);
    }
}
