package com.registationSystem.regSys.service.impl;

import com.registationSystem.regSys.dao.StudentDAO;
import com.registationSystem.regSys.repository.StudentsRepository;
import com.registationSystem.regSys.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentsRepository studentsRepository;

    public void create(StudentDAO studentDAO) {
        studentsRepository.save(studentDAO);
    }

    public List<StudentDAO> readAll() {
        return studentsRepository.findAll();
    }

    public StudentDAO read(int id) {
        return studentsRepository.existsById(id)?studentsRepository.findById(id).get():null;
    }

    public void update(StudentDAO studentDAO, int id) {
        if (studentsRepository.existsById(id)) {
            studentDAO.setId(id);
            studentsRepository.save(studentDAO);
        }
    }

    public boolean delete(int id) {
        if (studentsRepository.existsById(id)) {
            studentsRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
