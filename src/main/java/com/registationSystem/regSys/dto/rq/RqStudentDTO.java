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
public class RqStudentDTO {
    @Schema(description = "Имя студента", example = "Билли")
    private String firstName;
    @NotNull
    @Schema(description = "Фамилия студента", example = "Херрингтон")
    private String surname;
    @NotNull
    @Schema(description = "Возраст студента", example = "18")
    private Integer age;
    @Schema(description = "Группа студента", example = "1", nullable = true)
    private Integer groupId;
}
