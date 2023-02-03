package com.registationSystem.regSys.dto.rs;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "ID посещаемости", example = "1", nullable = true)
    private Integer id;
    @Schema(description = "ID студента", example = "1")
    private Integer studentId;
    @Schema(description = "ID занятия", example = "1")
    private Integer lessonId;
    @Schema(description = "Имя студента", example = "Билли")
    private String studentName;
    @Schema(description = "Дата занятия", example = "2023-01-01")
    private Date date;
    @Schema(description = "Время занятия", example = "16:30:00")
    private Time time;
    @Schema(description = "Присутствие студента на занятии", example = "true")
    private Boolean attend;
    @Schema(description = "Предупреждал ли студент об отсутствии", example = "false")
    private Boolean warn;

}
