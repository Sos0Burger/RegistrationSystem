package com.registationSystem.regSys.IController;

import com.registationSystem.regSys.Models.LessonModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


public interface ILessonController {
    @PostMapping("/{coach_id}/{group_id}")
    ResponseEntity<?> create(@RequestBody LessonModel lessonModel, @PathVariable(name = "coach_id")int coachId);
}
