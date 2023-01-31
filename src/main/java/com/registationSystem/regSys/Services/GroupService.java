package com.registationSystem.regSys.Services;

import com.registationSystem.regSys.Entities.Group;
import com.registationSystem.regSys.Repositories.GroupsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    @Autowired
    private GroupsRepository groupsRepository;

    public void create(Group group) {
        groupsRepository.save(group);
    }

    public List<Group> readAll() {
        return groupsRepository.findAll();
    }

    public Group read(int id) {
        return groupsRepository.existsById(id)?groupsRepository.findById(id).get():null;
    }

    public void update(Group group, int id) {
        if (groupsRepository.existsById(id)) {
            group.setId(id);
            groupsRepository.save(group);
        }
    }

    public boolean delete(int id) {
        if (groupsRepository.existsById(id)) {
            groupsRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
