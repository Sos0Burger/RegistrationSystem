package com.registationSystem.regSys.controller.controllerImpl;

import com.registationSystem.regSys.controller.CoachesController;
import com.registationSystem.regSys.dao.CoachDAO;
import com.registationSystem.regSys.dao.LessonDAO;
import com.registationSystem.regSys.dto.rs.RsCoachDTO;
import com.registationSystem.regSys.dto.rs.RsLessonDTO;
import com.registationSystem.regSys.Converter;
import com.registationSystem.regSys.Services.CoachService;
import com.registationSystem.regSys.Services.LessonService;
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
public class CoachesControllerImpl implements CoachesController {
    private final CoachService coachService;
    private final LessonService lessonService;

    @Autowired
    public CoachesControllerImpl(CoachService coachService, LessonService lessonService) {
        this.coachService = coachService;
        this.lessonService = lessonService;
    }

    @Override
    public ResponseEntity<?> create(@RequestBody RsCoachDTO rsCoachDTO) {
        coachService.create(Converter.coachModelToCoachEntity(rsCoachDTO,this));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @Override
    public ResponseEntity<List<RsCoachDTO>> readAll(){
        List<RsCoachDTO> rsCoachDTOS = new ArrayList<>();
        for (CoachDAO coachDAO : coachService.readAll()
                ) {
            rsCoachDTOS.add(Converter.coachToCoachModel(coachDAO));
        }
        return new ResponseEntity<>(rsCoachDTOS, HttpStatus.OK);
    }

    @Override
    public  ResponseEntity<List<RsLessonDTO>> getUnfinishedLessons(@RequestParam(name="id")int id){
        List<RsLessonDTO> rsLessonDTOS = new ArrayList<>();
        Set<LessonDAO> lessonDAOSet = coachService.read(id).getLessonDAOList();
        lessonDAOSet.removeIf(LessonDAO::isDone);
        for (LessonDAO lessonDAO : lessonDAOSet
             ) {
            rsLessonDTOS.add(Converter.lessonEntityToLessonModel(lessonDAO));
        }
        return new ResponseEntity<>(rsLessonDTOS, HttpStatus.OK);
    }
    @Override
    public ResponseEntity<List<RsLessonDTO>> getFinishedLessons(@RequestParam(name="id")int id){
        List<RsLessonDTO> rsLessonDTOS = new ArrayList<>();
        Set<LessonDAO> lessonDAOSet = coachService.read(id).getLessonDAOList();
        lessonDAOSet.removeIf(lesson -> !lesson.isDone());
        for (LessonDAO lessonDAO : lessonDAOSet
        ) {
            rsLessonDTOS.add(Converter.lessonEntityToLessonModel(lessonDAO));
        }
        return new ResponseEntity<>(rsLessonDTOS, HttpStatus.OK);
    }
}
