package com.registationSystem.regSys.dto.rq;


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
public class RqLessonDTO {
    private Time time;
    private Date date;
    private Integer groupId;
    private Integer coachId;
}
