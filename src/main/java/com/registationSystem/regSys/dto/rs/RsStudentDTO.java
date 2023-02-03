package com.registationSystem.regSys.dto.rs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RsStudentDTO {
    @Schema(description = "ID студента", example = "1")
    private Integer id;
    @Schema(description = "Имя студента", example = "Билли")
    private String firstName;
    @Schema(description = "Фамилия студента", example = "Херрингтон")
    private String surname;
    @Schema(description = "Возраст студента", example = "18")
    private Integer age;
    @Schema(description = "ID группы", example = "1")
    private Integer groupId;
}
