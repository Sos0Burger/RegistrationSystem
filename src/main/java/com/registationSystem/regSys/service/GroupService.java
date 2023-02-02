package com.registationSystem.regSys.service;

import com.registationSystem.regSys.dao.GroupDAO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface GroupService {
    void create(GroupDAO groupDAO);
    List<GroupDAO> readAll();

    GroupDAO read(int id);

    void update(GroupDAO groupDAO, int id);

   boolean delete(int id);
}
