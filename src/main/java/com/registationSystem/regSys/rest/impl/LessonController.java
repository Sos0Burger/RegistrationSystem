package com.registationSystem.regSys.rest.impl;


import com.registationSystem.regSys.dto.rq.RqLessonDTO;
import com.registationSystem.regSys.exception.CreationException;
import com.registationSystem.regSys.rest.LessonApi;
import com.registationSystem.regSys.service.impl.CoachServiceImpl;
import com.registationSystem.regSys.service.impl.GroupServiceImpl;
import com.registationSystem.regSys.service.impl.LessonServiceImpl;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@Getter
public class LessonController implements LessonApi {

    private final LessonServiceImpl lessonService;
    private final GroupServiceImpl groupService;
    private final CoachServiceImpl coachService;

    @Autowired
    LessonController(LessonServiceImpl lessonService, GroupServiceImpl groupService, CoachServiceImpl coachService) {
        this.lessonService = lessonService;
        this.groupService = groupService;
        this.coachService = coachService;
    }

    @Override
    public ResponseEntity<?> create(@RequestBody RqLessonDTO rqLessonDTO) throws CreationException {
        try {
            return lessonService.create(rqLessonDTO);
        } catch (NoSuchElementException ex) {
            throw new CreationException("ID не существует");
        }
    }

}
