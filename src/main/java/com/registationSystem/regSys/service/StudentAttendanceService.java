package com.registationSystem.regSys.service;

import com.registationSystem.regSys.dao.StudentAttendanceDAO;
import com.registationSystem.regSys.dto.rq.RqStudentAttendanceDTO;
import com.registationSystem.regSys.dto.rs.RsStudentAttendanceDTO;

import java.util.List;

public interface StudentAttendanceService {
    void create(RqStudentAttendanceDTO rqStudentAttendanceDTO);

    StudentAttendanceDAO find(int id);
    List<RsStudentAttendanceDTO> findAll();

    void update(RqStudentAttendanceDTO rqStudentAttendanceDTO, int id);
}
