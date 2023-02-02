package com.registationSystem.regSys.dto.rq;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RqStudentAttendanceDTO {
    private int studentId;
    private int lessonId;
    private boolean attend;
    private boolean warn;

}
