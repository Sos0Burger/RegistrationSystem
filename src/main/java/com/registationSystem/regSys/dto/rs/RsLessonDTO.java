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
public class RsLessonDTO {
    @Schema(description = "ID занятия", example = "1")
    private Integer id;
    @Schema(description = "Время занятия", example = "18:00:00")
    private Time time;
    @Schema(description = "Дата занятия", example = "2023-01-01")
    private Date date;
    @Schema(description = "ID группы", example = "1")
    private Integer groupId;
    @Schema(description = "Закончено ли занятие", example = "true")
    private Boolean isDone;
    @Schema(description = "Имя тренера", example = "Ван Даркхолм")
    private String coachName;
}
