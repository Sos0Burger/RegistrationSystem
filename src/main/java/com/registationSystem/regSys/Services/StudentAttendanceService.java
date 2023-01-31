package com.registationSystem.regSys.Services;

import com.registationSystem.regSys.Entities.StudentAttendance;
import com.registationSystem.regSys.Repositories.StudentAttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class StudentAttendanceService {
    @Autowired
    private StudentAttendanceRepository studentAttendanceRepository;

    public void create(StudentAttendance studentAttendance) {
        studentAttendanceRepository.save(studentAttendance);
    }

    public StudentAttendance read(int id) {
        return studentAttendanceRepository.findById(id).get();
    }

}
