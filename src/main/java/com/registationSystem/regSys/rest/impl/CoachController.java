package com.registationSystem.regSys.rest.impl;

import com.registationSystem.regSys.dto.rq.RqCoachDTO;
import com.registationSystem.regSys.rest.CoachApi;
import com.registationSystem.regSys.dao.CoachDAO;
import com.registationSystem.regSys.dao.LessonDAO;
import com.registationSystem.regSys.dto.rs.RsCoachDTO;
import com.registationSystem.regSys.dto.rs.RsLessonDTO;
import com.registationSystem.regSys.mapper.Mapper;
import com.registationSystem.regSys.service.impl.CoachServiceImpl;
import com.registationSystem.regSys.service.impl.LessonServiceImpl;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@Getter
public class CoachController implements CoachApi {
    private final CoachServiceImpl coachService;

    @Autowired
    public CoachController(CoachServiceImpl coachService) {
        this.coachService = coachService;
    }
    @Override
    public ResponseEntity<?> create(@RequestBody RqCoachDTO rqCoachDTO) {
        coachService.create(rqCoachDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @Override
    public ResponseEntity<List<RsCoachDTO>> readAll(){
        return new ResponseEntity<>(coachService.readAll(), HttpStatus.OK);
    }

    @Override
    public  ResponseEntity<List<RsLessonDTO>> getUnfinishedLessons(@RequestParam(name="id")int coachId){
        return new ResponseEntity<>(coachService.getUnfinishedLessons(coachId), HttpStatus.OK);
    }
    @Override
    public ResponseEntity<List<RsLessonDTO>> getFinishedLessons(@RequestParam(name="id")int coachId){
        return new ResponseEntity<>(coachService.getFinishedLessons(coachId), HttpStatus.OK);
    }
}
