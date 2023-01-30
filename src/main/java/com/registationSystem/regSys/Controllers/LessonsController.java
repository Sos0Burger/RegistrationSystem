package com.registationSystem.regSys.Controllers;

import com.registationSystem.regSys.ControllerInterInterfaces.ILessonController;
import com.registationSystem.regSys.Models.Lesson;
import com.registationSystem.regSys.Services.LessonService;
import com.registationSystem.regSys.config.ApplicationSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class LessonsController implements ILessonController {

    private final LessonService lessonService;
    @Autowired
    LessonsController(LessonService lessonService){
        this.lessonService = lessonService;
    }

    @Override
    public ResponseEntity<?> create(@RequestBody Lesson lesson) {
        List<Lesson> dateLessonsList = lessonService.findByDate(lesson.getDate());
        if(dateLessonsList!=null){
            ArrayList<Time> times = new ArrayList<>();
            times.add(ApplicationSettings.LESSON_SART_TIME);
            times.add(ApplicationSettings.LESSON_END_TIME);

            //Проверка возможности записи на это время
            for (Lesson item:dateLessonsList
                 ) {
                times.add(item.getTime());
            }

            Collections.sort(times);
            for(int i = 0;i<times.size()-1;i++){
                if(toMinutes(times.get(i))+60<=toMinutes(lesson.getTime()) &
                        toMinutes(times.get(i+1))-60>=toMinutes(lesson.getTime())){
                    lessonService.create(lesson);
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
        System.out.println(time.getHours()*60+time.getMinutes());
        return (time.getHours()*60+time.getMinutes());
    }
}
