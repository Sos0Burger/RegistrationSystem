package com.registationSystem.regSys.Services;

import com.registationSystem.regSys.dao.StudentAttendanceDAO;
import com.registationSystem.regSys.repository.StudentAttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentAttendanceService {
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
