package com.registationSystem.regSys.Services;

import com.registationSystem.regSys.Entities.Student;
import com.registationSystem.regSys.Repositories.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentsRepository studentsRepository;

    public void create(Student student) {
        studentsRepository.save(student);
    }

    public List<Student> readAll() {
        return studentsRepository.findAll();
    }

    public Student read(int id) {
        return studentsRepository.findById(id).get();
    }

    public void update(Student student, int id) {
        if (studentsRepository.existsById(id)) {
            student.setId(id);
            studentsRepository.save(student);
        }
    }

    public boolean delete(int id) {
        if (studentsRepository.existsById(id)) {
            studentsRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public List<Student> findByGroupId(int id){
        return studentsRepository.findByGroupId(id);
    }
}
