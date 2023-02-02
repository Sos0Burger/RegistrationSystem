package com.registationSystem.regSys.dto.rs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RsStudentDTO {
    private Integer id;
    private String firstName;
    private String surname;
    private Integer age;
    private Integer groupId;
}
