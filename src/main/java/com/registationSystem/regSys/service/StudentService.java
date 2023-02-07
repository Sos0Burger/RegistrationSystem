package com.registationSystem.regSys.service;

import com.registationSystem.regSys.dao.StudentDAO;
import com.registationSystem.regSys.dto.rq.RqStudentDTO;
import com.registationSystem.regSys.dto.rs.RsLessonDTO;
import com.registationSystem.regSys.dto.rs.RsStudentDTO;
import com.registationSystem.regSys.exception.RegistrationException;

import java.util.List;

public interface StudentService {
    void create(RqStudentDTO rqStudentDTO) throws RegistrationException;

    List<RsStudentDTO> findAll();

    StudentDAO find(int id);

    void update(RqStudentDTO rqStudentDTO, int id);

    void delete(int id);

    List<RsLessonDTO> getScheduleById(int id);
}
