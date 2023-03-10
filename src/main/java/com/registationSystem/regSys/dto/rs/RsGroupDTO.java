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
public class RsGroupDTO {
    @Schema(description = "ID группы", example = "1")
    private Integer id;
    @Schema(description = "Размер группы", example = "13")
    private Integer size;
    @Schema(description = "Минимальный возраст", example = "10")
    private Integer minAge;
    @Schema(description = "Максимальный возраст", example = "12")
    private Integer maxAge;
}
