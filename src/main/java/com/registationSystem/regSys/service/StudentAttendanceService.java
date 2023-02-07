package com.registationSystem.regSys.service;

import com.registationSystem.regSys.dao.StudentAttendanceDAO;
import com.registationSystem.regSys.dto.rq.RqStudentAttendanceDTO;

public interface StudentAttendanceService {
    void create(RqStudentAttendanceDTO rqStudentAttendanceDTO);

    StudentAttendanceDAO find(int id);

    void update(RqStudentAttendanceDTO rqStudentAttendanceDTO, int id);
}
