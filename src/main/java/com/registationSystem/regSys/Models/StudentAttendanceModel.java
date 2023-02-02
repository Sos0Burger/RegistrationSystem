package com.registationSystem.regSys.Models;

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
public class StudentAttendanceModel {
    private int id;
    private int studentId;
    private int lessonId;
    private String studentName;
    private Date date;
    private Time time;
    private boolean attend;
    private boolean warn;

}
