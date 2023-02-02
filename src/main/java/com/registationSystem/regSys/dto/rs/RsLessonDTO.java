package com.registationSystem.regSys.dto.rs;

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
public class RsLessonDTO {
    private int id;
    private Time time;
    private Date date;
    private int groupId;
    private boolean isDone;
    private String coachName;
}
