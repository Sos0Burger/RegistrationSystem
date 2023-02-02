package com.registationSystem.regSys.controller.controllerImpl;


import com.registationSystem.regSys.dao.LessonDAO;
import com.registationSystem.regSys.dto.rs.RsLessonDTO;
import com.registationSystem.regSys.Converter;
import com.registationSystem.regSys.Services.CoachService;
import com.registationSystem.regSys.Services.GroupService;
import com.registationSystem.regSys.Services.LessonService;
import com.registationSystem.regSys.config.ApplicationSettings;
import com.registationSystem.regSys.controller.LessonController;
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
@RequestMapping("/lessons")
@Getter
public class LessonsControllerImpl implements LessonController {

    private final LessonService lessonService;
    private final GroupService groupService;
    private final CoachService coachService;
    @Autowired
    LessonsControllerImpl(LessonService lessonService, GroupService groupService, CoachService coachService){
        this.lessonService = lessonService;
        this.groupService = groupService;
        this.coachService = coachService;
    }

    @Override
    public ResponseEntity<?> create(@RequestBody RsLessonDTO rsLessonDTO, @PathVariable(name = "coach_id")int coachId ) {
        LessonDAO lessonDAO = Converter.lessonModelToLessonEntity(rsLessonDTO, this);
        //Поиск группы по ID
        if(groupService.read(rsLessonDTO.getGroupId())!=null){
            lessonDAO.setGroupDAO(groupService.read(rsLessonDTO.getGroupId()));
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //Поиск тренера по ID
        if(coachService.read(coachId)!=null){
            lessonDAO.setCoachDAO(coachService.read(coachId));
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<LessonDAO> dateLessonsList = lessonService.findByDate(lessonDAO.getDate());
        if(dateLessonsList!=null){
            ArrayList<Time> times = new ArrayList<>();
            times.add(ApplicationSettings.LESSON_START_TIME);
            times.add(ApplicationSettings.LESSON_END_TIME);

            //Проверка возможности записи на это время
            for (LessonDAO item:dateLessonsList
                 ) {
                times.add(item.getTime());
            }
            Collections.sort(times);
            for(int i = 0;i<times.size()-1;i++){
                if(toMinutes(times.get(i))+60<=toMinutes(lessonDAO.getTime()) &
                        toMinutes(times.get(i+1))-60>=toMinutes(lessonDAO.getTime())){
                    lessonDAO.setCoachDAO(coachService.read(coachId));
                    lessonDAO.setGroupDAO(groupService.read(rsLessonDTO.getGroupId()));
                    lessonService.create(lessonDAO);
                    return new ResponseEntity<>(HttpStatus.CREATED);
                }
            }
            //Это надо поменять, наверное
            return new ResponseEntity<>(HttpStatus.CONFLICT);


        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public int toMinutes(Time time){
        return (time.getHours()*60+time.getMinutes());
    }
}
