package com.registationSystem.regSys.service;

import com.registationSystem.regSys.dao.StudentDAO;
import com.registationSystem.regSys.dto.rq.RqStudentDTO;
import com.registationSystem.regSys.dto.rs.RsLessonDTO;
import com.registationSystem.regSys.dto.rs.RsStudentDTO;

import java.util.List;

public interface StudentService {
    void create(RqStudentDTO rqStudentDTO);

    List<RsStudentDTO> readAll();

    StudentDAO read(int id);

    void update(RqStudentDTO rqStudentDTO, int id);

    void delete(int id);

    List<RsLessonDTO> getScheduleById(int id);
}
