package com.registationSystem.regSys.service;

import com.registationSystem.regSys.dao.CoachDAO;
import com.registationSystem.regSys.dto.rq.RqCoachDTO;
import com.registationSystem.regSys.dto.rs.RsCoachDTO;
import com.registationSystem.regSys.dto.rs.RsLessonDTO;

import java.util.List;

public interface CoachService {
    void create(RqCoachDTO rqCoachDTO);

    List<RsCoachDTO> findAll();

    RsCoachDTO find(int id);

    void update(RqCoachDTO rqCoachDTO, int id);

    void delete(int id);

    List<RsLessonDTO> getUnfinishedLessons(int coachId);

    List<RsLessonDTO> getFinishedLessons(int coachId);
}
