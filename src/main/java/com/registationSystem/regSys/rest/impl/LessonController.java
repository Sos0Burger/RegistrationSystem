package com.registationSystem.regSys.rest.impl;


import com.registationSystem.regSys.dto.rq.RqLessonDTO;
import com.registationSystem.regSys.exception.CreationException;
import com.registationSystem.regSys.exception.UpdateException;
import com.registationSystem.regSys.rest.LessonApi;
import com.registationSystem.regSys.service.LessonService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@Getter
public class LessonController implements LessonApi {

    private final LessonService lessonService;


    @Autowired
    LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @Override
    public ResponseEntity<?> create(@RequestBody RqLessonDTO rqLessonDTO) throws CreationException {
        try {
            return lessonService.create(rqLessonDTO);
        } catch (NoSuchElementException ex) {
            throw new CreationException("ID не существует");
        }
    }

    @Override
    public ResponseEntity<?> update(RqLessonDTO rqLessonDTO, int lessonId) throws UpdateException {
        try {
            return lessonService.update(rqLessonDTO, lessonId);
        }catch (NoSuchElementException ex){
            throw new UpdateException("ID не найден");
        }

    }
}
