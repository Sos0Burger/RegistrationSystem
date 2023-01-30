package com.registationSystem.regSys.ControllerInterInterfaces;

import com.registationSystem.regSys.Models.Lesson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


public interface ILessonController {
    @PostMapping("/Lessons")
    ResponseEntity<?> create(@RequestBody Lesson lesson);
}
