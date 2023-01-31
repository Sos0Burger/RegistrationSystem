package com.registationSystem.regSys.IController;

import com.registationSystem.regSys.Models.Lesson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


public interface ILessonController {
    @PostMapping("/createLesson/{coach_id}/{group_id}")
    ResponseEntity<?> create(@RequestBody Lesson lesson, @PathVariable(name = "coach_id")int id, @PathVariable(name = "group_id")int groupId);
}
