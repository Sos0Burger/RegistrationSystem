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
public class RsCoachDTO {
    @Schema(description = "ID тренера", example = "12")
    private Integer id;
    @Schema(description = "Имя тренера", example = "Ван")
    private String name;
    @Schema(description = "Фамилия тренера", example = "Даркхолм")
    private String surname;
    @Schema(description = "Номер телефона тренера", example = "89447166451")
    private String phoneNumber;
    @Schema(description = "Email тренера", example = "example@mail.com")
    private String email;
}
