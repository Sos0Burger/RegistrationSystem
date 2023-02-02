package com.registationSystem.regSys.dto.rs;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RsGroupDTO {
    private int id;
    private int size;
    private int minAge;
    private int maxAge;
}
