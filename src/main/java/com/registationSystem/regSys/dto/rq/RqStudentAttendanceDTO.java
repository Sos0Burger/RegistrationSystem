package com.registationSystem.regSys.dto.rq;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RqStudentAttendanceDTO {
    @Schema(description = "ID посещаемости", example = "1", nullable = true)
    private Integer id;
    @NotNull
    @Schema(description = "ID студента", example = "1")
    private Integer studentId;
    @NotNull
    @Schema(description = "ID занятия", example = "1")
    private Integer lessonId;
    @NotNull
    @Schema(description = "Присутствие студента на занятии", example = "true")
    private Boolean attend;
    @NotNull
    @Schema(description = "Предупреждал ли студент об отсутствии", example = "false")
    private Boolean warn;

}
