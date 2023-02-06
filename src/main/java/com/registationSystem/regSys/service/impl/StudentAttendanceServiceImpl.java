package com.registationSystem.regSys.service.impl;

import com.registationSystem.regSys.dao.StudentAttendanceDAO;
import com.registationSystem.regSys.dto.rq.RqStudentAttendanceDTO;
import com.registationSystem.regSys.mapper.Mapper;
import com.registationSystem.regSys.repository.LessonsRepository;
import com.registationSystem.regSys.repository.StudentAttendanceRepository;
import com.registationSystem.regSys.repository.StudentsRepository;
import com.registationSystem.regSys.service.StudentAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class StudentAttendanceServiceImpl implements StudentAttendanceService {
    @Autowired
    private StudentAttendanceRepository studentAttendanceRepository;
    @Autowired
    private LessonsRepository lessonsRepository;
    @Autowired
    StudentsRepository studentsRepository;

    public void create(RqStudentAttendanceDTO rqStudentAttendanceDTO) {
        studentsRepository.findById(rqStudentAttendanceDTO.getStudentId()).get();
        lessonsRepository.findById(rqStudentAttendanceDTO.getLessonId()).get();
        try {
            if(rqStudentAttendanceDTO.getId()!=null) {
                studentAttendanceRepository.findById(rqStudentAttendanceDTO.getId()).get();
            }
            update(rqStudentAttendanceDTO, rqStudentAttendanceDTO.getId());
        } catch (NoSuchElementException ex) {
            studentAttendanceRepository.save(Mapper.studentAttendanceDTOToStudentAttendanceDAO(rqStudentAttendanceDTO));
        }
    }

    public StudentAttendanceDAO read(int id) {
        return studentAttendanceRepository.findById(id).get();
    }

    public void update(RqStudentAttendanceDTO rqStudentAttendanceDTO, int id) {
        studentAttendanceRepository.findById(id).get();
        rqStudentAttendanceDTO.setId(id);
        studentAttendanceRepository.save(Mapper.studentAttendanceDTOToStudentAttendanceDAO(rqStudentAttendanceDTO));
    }

}
