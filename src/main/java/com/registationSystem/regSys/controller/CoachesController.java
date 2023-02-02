package com.registationSystem.regSys.controller;

import com.registationSystem.regSys.dto.rs.RsCoachDTO;
import com.registationSystem.regSys.dto.rs.RsLessonDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CoachesController {
    @PostMapping()
    ResponseEntity<?> create(@RequestBody RsCoachDTO rsCoachDTO);
    @GetMapping()
    ResponseEntity<List<RsCoachDTO>> readAll();

    @GetMapping("/{id}/unfinishedLessons")
    ResponseEntity<List<RsLessonDTO>> getUnfinishedLessons(@RequestParam(name="id")int id);
    @GetMapping("/{id}/finishedLessons")
    ResponseEntity<List<RsLessonDTO>> getFinishedLessons(@RequestParam(name="id")int id);
}
