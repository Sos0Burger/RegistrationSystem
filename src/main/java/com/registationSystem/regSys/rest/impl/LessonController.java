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
        LessonDAO lessonDAO = Mapper.lessonDTOToLessonDAO(rqLessonDTO);
        //Поиск группы по ID
        if(groupService.read(rqLessonDTO.getGroupId())!=null){
            lessonDAO.setGroupDAO(groupService.read(rqLessonDTO.getGroupId()));
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //Поиск тренера по ID
        if(coachService.read(rqLessonDTO.getCoachId())!=null){
            lessonDAO.setCoachDAO(coachService.read(rqLessonDTO.getCoachId()));
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
                    lessonDAO.setCoachDAO(coachService.read(rqLessonDTO.getCoachId()));
                    lessonDAO.setGroupDAO(groupService.read(rqLessonDTO.getGroupId()));
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
