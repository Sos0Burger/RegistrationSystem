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
public class RsStudentAttendanceDTO {
    private Integer id;
    private Integer studentId;
    private Integer lessonId;
    private String studentName;
    private Date date;
    private Time time;
    private Boolean attend;
    private Boolean warn;

}
