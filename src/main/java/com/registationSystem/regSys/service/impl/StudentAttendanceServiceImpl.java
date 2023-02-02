package com.registationSystem.regSys.service.impl;

import com.registationSystem.regSys.dao.StudentAttendanceDAO;
import com.registationSystem.regSys.repository.StudentAttendanceRepository;
import com.registationSystem.regSys.service.StudentAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentAttendanceServiceImpl implements StudentAttendanceService {
    @Autowired
    private StudentAttendanceRepository studentAttendanceRepository;

    public void create(StudentAttendanceDAO studentAttendanceDAO) {
        studentAttendanceRepository.save(studentAttendanceDAO);
    }

    public StudentAttendanceDAO read(int id) {
        return studentAttendanceRepository.existsById(id)?studentAttendanceRepository.findById(id).get():null;
    }
    public void update(StudentAttendanceDAO studentAttendanceDAO, int id){
        if (studentAttendanceRepository.existsById(id)) {
            studentAttendanceDAO.setId(id);
            studentAttendanceRepository.save(studentAttendanceDAO);
        }
    }

}
