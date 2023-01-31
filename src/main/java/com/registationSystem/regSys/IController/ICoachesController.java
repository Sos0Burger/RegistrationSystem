package com.registationSystem.regSys.IController;

import com.registationSystem.regSys.Entities.Coach;
import com.registationSystem.regSys.Entities.Lesson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

public interface ICoachesController {
    @PostMapping("/coaches")
    ResponseEntity<?> create(@RequestBody Coach coach);
    @GetMapping("/coaches")
    List<Coach> readAll();

    @GetMapping("/coaches/{id}/unfinishedLessons")
    Set<Lesson> getUnfinishedLessons(@RequestParam(name="id")int id);
    @GetMapping("/coaches/{id}/finishedLessons")
    Set<Lesson> getFinishedLessons(@RequestParam(name="id")int id);
}