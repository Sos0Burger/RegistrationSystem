package com.registationSystem.regSys.service;

import com.registationSystem.regSys.dao.StudentDAO;
import com.registationSystem.regSys.dto.rq.RqStudentDTO;

import java.util.List;
import java.util.NoSuchElementException;

public interface StudentService {
    void create(RqStudentDTO rqStudentDTO) throws NoSuchElementException;

    List<StudentDAO> readAll();

    StudentDAO read(int id);

    void update(RqStudentDTO rqStudentDTO, int id);

    boolean delete(int id);
}
