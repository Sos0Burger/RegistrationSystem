package com.registationSystem.regSys.Services;

import com.registationSystem.regSys.Models.Student;
import com.registationSystem.regSys.Models.StudentAttendance;
import com.registationSystem.regSys.Repositories.StudentAttendanceRepository;
import com.registationSystem.regSys.Repositories.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StudentAttendanceService {
    @Autowired
    private StudentAttendanceRepository studentAttendanceRepository;

    public void create(List<StudentAttendance> studentAttendanceList) {
        for (StudentAttendance item:studentAttendanceList
             ) {
            if(!studentAttendanceRepository.existsById(item.getId())){
                studentAttendanceRepository.save(item);
            }
        }
    }

    public StudentAttendance read(int id) {
        return studentAttendanceRepository.findById(id).get();
    }

    public List<StudentAttendance> findByStudentId(int id){
        return studentAttendanceRepository.findByStudentId(id);
    }
}
