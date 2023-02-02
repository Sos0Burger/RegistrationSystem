package com.registationSystem.regSys.service;

import com.registationSystem.regSys.dao.CoachDAO;

import java.util.List;

public interface CoachService {
    void create(CoachDAO coachDAO);

    List<CoachDAO> readAll();

    CoachDAO read(int id);

    void update(CoachDAO coachDAO, int id);

    boolean delete(int id);
}
