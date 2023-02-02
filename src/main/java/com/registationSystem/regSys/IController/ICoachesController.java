package com.registationSystem.regSys.IController;

import com.registationSystem.regSys.Entities.Coach;
import com.registationSystem.regSys.Entities.Lesson;
import com.registationSystem.regSys.Models.CoachModel;
import com.registationSystem.regSys.Models.LessonModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

public interface ICoachesController {
    @PostMapping()
    ResponseEntity<?> create(@RequestBody CoachModel coachModel);
    @GetMapping()
    ResponseEntity<List<CoachModel>> readAll();

    @GetMapping("/{id}/unfinishedLessons")
    ResponseEntity<List<LessonModel>> getUnfinishedLessons(@RequestParam(name="id")int id);
    @GetMapping("/{id}/finishedLessons")
    ResponseEntity<List<LessonModel>> getFinishedLessons(@RequestParam(name="id")int id);
}
