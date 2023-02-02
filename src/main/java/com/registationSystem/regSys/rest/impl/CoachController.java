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

@RequestMapping("/coaches")
@RestController
@Getter
public class CoachController implements CoachApi {
    private final CoachServiceImpl coachService;
    private final LessonServiceImpl lessonService;

    @Autowired
    public CoachController(CoachServiceImpl coachService, LessonServiceImpl lessonService) {
        this.coachService = coachService;
        this.lessonService = lessonService;
    }

    @Override
    public ResponseEntity<?> create(@RequestBody RqCoachDTO rqCoachDTO) {
        coachService.create(Mapper.coachDTOToCoachDAO(rqCoachDTO));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @Override
    public ResponseEntity<List<RsCoachDTO>> readAll(){
        List<RsCoachDTO> rsCoachDTOS = new ArrayList<>();
        for (CoachDAO coachDAO : coachService.readAll()
                ) {
            rsCoachDTOS.add(Mapper.coachDAOToCoachDTO(coachDAO));
        }
        return new ResponseEntity<>(rsCoachDTOS, HttpStatus.OK);
    }

    @Override
    public  ResponseEntity<List<RsLessonDTO>> getUnfinishedLessons(@RequestParam(name="id")int id){
        List<RsLessonDTO> rsLessonDTOS = new ArrayList<>();
        Set<LessonDAO> lessonDAOSet = coachService.read(id).getLessonDAOList();
        lessonDAOSet.removeIf(LessonDAO::getIsDone);
        for (LessonDAO lessonDAO : lessonDAOSet
             ) {
            rsLessonDTOS.add(Mapper.lessonDAOToLessonDTO(lessonDAO));
        }
        return new ResponseEntity<>(rsLessonDTOS, HttpStatus.OK);
    }
    @Override
    public ResponseEntity<List<RsLessonDTO>> getFinishedLessons(@RequestParam(name="id")int id){
        List<RsLessonDTO> rsLessonDTOS = new ArrayList<>();
        Set<LessonDAO> lessonDAOSet = coachService.read(id).getLessonDAOList();
        lessonDAOSet.removeIf(lesson -> !lesson.getIsDone());
        for (LessonDAO lessonDAO : lessonDAOSet
        ) {
            rsLessonDTOS.add(Mapper.lessonDAOToLessonDTO(lessonDAO));
        }
        return new ResponseEntity<>(rsLessonDTOS, HttpStatus.OK);
    }
}
