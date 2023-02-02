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
    private int id;
    private String firstName;
    private String surname;
    private int age;
    private int groupId;
}
