package com.registationSystem.regSys.Models;

import com.registationSystem.regSys.Entities.Coach;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CoachModel {
    private int id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
}
