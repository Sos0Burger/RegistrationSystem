package com.registationSystem.regSys.dto.rq;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RqCoachDTO {
    @NotNull
    @Schema(description = "Имя тренера", example = "Ван")
    private String name;
    @NotNull
    @Schema(description = "Фамилия тренера", example = "Даркхолм")
    private String surname;
    @NotNull
    @Schema(description = "Номер телефона тренера", example = "89447166451")
    private String phoneNumber;
    @Email
    @NotNull
    @Schema(description = "Email тренера", example = "example@mail.com")
    private String email;
}
