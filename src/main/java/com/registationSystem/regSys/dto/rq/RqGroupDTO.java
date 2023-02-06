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
public class RqGroupDTO {
    @Schema(description = "Размер группы", example = "12")
    private Integer size;
    @NotNull
    @Schema(description = "Минимальный возраст", example = "10")
    private Integer minAge;
    @NotNull
    @Schema(description = "Максимальный возраст", example = "12")
    private Integer maxAge;
}