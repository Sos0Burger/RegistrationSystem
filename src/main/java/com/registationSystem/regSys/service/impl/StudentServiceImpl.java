package com.registationSystem.regSys.service.impl;

import com.registationSystem.regSys.dao.StudentDAO;
import com.registationSystem.regSys.dto.rq.RqStudentDTO;
import com.registationSystem.regSys.mapper.Mapper;
import com.registationSystem.regSys.repository.GroupsRepository;
import com.registationSystem.regSys.repository.StudentsRepository;
import com.registationSystem.regSys.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentsRepository studentsRepository;
    @Autowired
    private GroupsRepository groupsRepository;

    public void create(RqStudentDTO rqStudentDTO) throws NoSuchElementException {
        try {
            groupsRepository.findById(rqStudentDTO.getGroupId()).get();
        }
        catch (NoSuchElementException ex){
            throw new NoSuchElementException("Группы с таким ID не существует");
        }
        studentsRepository.save(Mapper.studentDTOToStudentDAO(rqStudentDTO));
    }

    public List<StudentDAO> readAll() {
        return studentsRepository.findAll();
    }

    public StudentDAO read(int id) {
        return studentsRepository.existsById(id)?studentsRepository.findById(id).get():null;
    }

    public void update(RqStudentDTO rqStudentDTO, int id) throws NoSuchElementException {
        StudentDAO studentDAO;
        try {
            studentDAO = read(id);

        }
        catch (NoSuchElementException ex){
            throw new NoSuchElementException("Студента с таким ID не существует");
        }
        try {
            studentDAO.setGroupDAO(groupsRepository.findById(id).get());
        }
        catch (NoSuchElementException ex){
            throw new NoSuchElementException("Группы с таким ID не существует");
        }

        studentDAO.setAge(rqStudentDTO.getAge());
        studentDAO.setName(rqStudentDTO.getFirstName());
        studentDAO.setSurname(rqStudentDTO.getSurname());
        studentsRepository.save(studentDAO);
    }

    public boolean delete(int id) {
        if (studentsRepository.existsById(id)) {
            studentsRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
