package com.registationSystem.regSys.dto.rq;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RqStudentDTO {
    private String firstName;
    private String surname;
    private int age;
    private Integer groupId;
}
