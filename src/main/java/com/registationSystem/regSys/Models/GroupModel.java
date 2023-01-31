package com.registationSystem.regSys.Models;


import com.registationSystem.regSys.Entities.Group;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupModel {
    private int id;
    private int size;
    private int minAge;
    private int maxAge;
    public static GroupModel parseGroupModel(Group group){
        return new GroupModel(group.getId(), group.getSize(), group.getMinAge(), group.getMaxAge());
    }
}
