package com.registationSystem.regSys.service;

import com.registationSystem.regSys.dao.StudentDAO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StudentService {
    void create(StudentDAO studentDAO);

    List<StudentDAO> readAll();

    StudentDAO read(int id);

    void update(StudentDAO studentDAO, int id);

    boolean delete(int id);
}
