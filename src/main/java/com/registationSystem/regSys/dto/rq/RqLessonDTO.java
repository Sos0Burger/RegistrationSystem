package com.registationSystem.regSys.dto.rq;


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
public class RqLessonDTO {
    @Schema(description = "Время занятия", example = "18:00:00")
    private Time time;
    @Schema(description = "Дата занятия", example = "2023-01-01")
    private Date date;
    @Schema(description = "ID группы", example = "1")
    private Integer groupId;
    @Schema(description = "ID тренера", example = "1")
    private Integer coachId;
}
