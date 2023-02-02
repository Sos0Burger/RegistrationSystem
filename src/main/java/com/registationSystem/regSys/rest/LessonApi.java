package com.registationSystem.regSys.rest;

import com.registationSystem.regSys.dto.rq.RqLessonDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/lessons")
public interface LessonApi {
    @PostMapping
    ResponseEntity<?> create(@RequestBody RqLessonDTO rqLessonDTO);
}
