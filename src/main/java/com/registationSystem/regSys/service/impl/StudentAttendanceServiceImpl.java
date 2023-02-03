package com.registationSystem.regSys.service.impl;

import com.registationSystem.regSys.dao.StudentAttendanceDAO;
import com.registationSystem.regSys.dto.rq.RqStudentAttendanceDTO;
import com.registationSystem.regSys.dto.rq.RqStudentDTO;
import com.registationSystem.regSys.mapper.Mapper;
import com.registationSystem.regSys.repository.StudentAttendanceRepository;
import com.registationSystem.regSys.service.StudentAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class StudentAttendanceServiceImpl implements StudentAttendanceService {
    @Autowired
    private StudentAttendanceRepository studentAttendanceRepository;

    public void create(RqStudentAttendanceDTO rqStudentAttendanceDTO) {
        try{
            studentAttendanceRepository.findById(rqStudentAttendanceDTO.getId()).get();
            update(rqStudentAttendanceDTO, rqStudentAttendanceDTO.getId());
        }
        catch (NoSuchElementException ex){
            studentAttendanceRepository.save(Mapper.studentAttendanceDTOToStudentAttendanceDAO(rqStudentAttendanceDTO));
        }
    }

    public StudentAttendanceDAO read(int id) {
        return studentAttendanceRepository.existsById(id)?studentAttendanceRepository.findById(id).get():null;
    }
    public void update(RqStudentAttendanceDTO rqStudentAttendanceDTO, int id){
        if (studentAttendanceRepository.existsById(id)) {
            rqStudentAttendanceDTO.setId(id);
            studentAttendanceRepository.save(Mapper.studentAttendanceDTOToStudentAttendanceDAO(rqStudentAttendanceDTO));
        }
    }

}
