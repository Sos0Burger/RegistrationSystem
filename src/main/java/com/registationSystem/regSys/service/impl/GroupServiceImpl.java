package com.registationSystem.regSys.service.impl;

import com.registationSystem.regSys.dao.GroupDAO;
import com.registationSystem.regSys.repository.GroupsRepository;
import com.registationSystem.regSys.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupsRepository groupsRepository;

    public void create(GroupDAO groupDAO) {
        groupsRepository.save(groupDAO);
    }

    public List<GroupDAO> readAll() {
        return groupsRepository.findAll();
    }

    public GroupDAO read(int id) {
        return groupsRepository.existsById(id)?groupsRepository.findById(id).get():null;
    }

    public void update(GroupDAO groupDAO, int id) {
        if (groupsRepository.existsById(id)) {
            groupDAO.setId(id);
            groupsRepository.save(groupDAO);
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
