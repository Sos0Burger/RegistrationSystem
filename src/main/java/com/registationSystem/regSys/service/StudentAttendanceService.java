package com.registationSystem.regSys.service;

import com.registationSystem.regSys.dao.StudentAttendanceDAO;

public interface StudentAttendanceService {
    void create(StudentAttendanceDAO studentAttendanceDAO);

    StudentAttendanceDAO read(int id);

    void update(StudentAttendanceDAO studentAttendanceDAO, int id);
}
