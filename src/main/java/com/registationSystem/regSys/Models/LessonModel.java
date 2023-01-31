package com.registationSystem.regSys.Models;

import com.registationSystem.regSys.Entities.Lesson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LessonModel {
    private int id;
    private Time time;
    private Date date;
    private int groupId;
    private boolean isDone;
    private String coachName;
    public static LessonModel parseLessonModel(Lesson lesson){
        return new LessonModel(lesson.getId(), lesson.getTime(), lesson.getDate(), lesson.getGroup().getId(), lesson.isDone(),
                lesson.getCoach().getName()+lesson.getCoach().getSurname());
    }
}
