package com.registationSystem.regSys.ControllerInterInterfaces;

import com.registationSystem.regSys.Models.Coach;
import com.registationSystem.regSys.Models.Lesson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ICoachesController {
    @PostMapping("/coaches")
    ResponseEntity<?> create(@RequestBody Coach coach);
    @GetMapping("/coaches")
    List<Coach> readAll();

    @GetMapping("/coaches/{id}/unfinishedLessons")
    List<Lesson> getUnfinishedLessons(@RequestParam(name="id")int id);
    @GetMapping("/coaches/{id}/finishedLessons")
    List<Lesson> getFinishedLessons(@RequestParam(name="id")int id);
}
