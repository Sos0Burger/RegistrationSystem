package com.registationSystem.regSys.controller;

import com.registationSystem.regSys.dto.rs.RsLessonDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


public interface LessonController {
    @PostMapping("/{coach_id}/{group_id}")
    ResponseEntity<?> create(@RequestBody RsLessonDTO rsLessonDTO, @PathVariable(name = "coach_id")int coachId);
}
