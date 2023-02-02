package com.registationSystem.regSys.Services;

import com.registationSystem.regSys.dao.StudentDAO;
import com.registationSystem.regSys.repository.StudentsRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@Component
public class StudentService {
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
    public List<StudentDAO> findByGroupId(int id){
        return studentsRepository.findByGroupId(id);
    }
}
