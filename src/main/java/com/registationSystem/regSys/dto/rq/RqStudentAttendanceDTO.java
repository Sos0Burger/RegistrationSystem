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
    private Integer id;
    private Integer studentId;
    private Integer lessonId;
    private Boolean attend;
    private Boolean warn;

}
