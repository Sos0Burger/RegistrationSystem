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
    private Integer id;
    private Integer size;
    private Integer minAge;
    private Integer maxAge;
}
