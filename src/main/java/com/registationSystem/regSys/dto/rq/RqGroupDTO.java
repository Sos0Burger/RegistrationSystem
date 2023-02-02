package com.registationSystem.regSys.dto.rq;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RqGroupDTO {
    private Integer size;
    private Integer minAge;
    private Integer maxAge;
}