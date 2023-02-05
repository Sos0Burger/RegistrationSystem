package com.registationSystem.regSys.rest.impl;


import com.registationSystem.regSys.dao.LessonDAO;
import com.registationSystem.regSys.dto.rq.RqLessonDTO;
import com.registationSystem.regSys.mapper.Mapper;
import com.registationSystem.regSys.service.impl.CoachServiceImpl;
import com.registationSystem.regSys.service.impl.GroupServiceImpl;
import com.registationSystem.regSys.service.impl.LessonServiceImpl;
import com.registationSystem.regSys.config.ApplicationSettings;
import com.registationSystem.regSys.rest.LessonApi;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@Getter
public class LessonController implements LessonApi {

    private final LessonServiceImpl lessonService;
    private final GroupServiceImpl groupService;
    private final CoachServiceImpl coachService;
    @Autowired
    LessonController(LessonServiceImpl lessonService, GroupServiceImpl groupService, CoachServiceImpl coachService){
        this.lessonService = lessonService;
        this.groupService = groupService;
        this.coachService = coachService;
    }

    @Override
    public ResponseEntity<?> create(@RequestBody RqLessonDTO rqLessonDTO) {
        return lessonService.create(rqLessonDTO);
    }

}
