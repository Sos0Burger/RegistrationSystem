package com.registationSystem.regSys.service.impl;

import com.registationSystem.regSys.dao.LessonDAO;
import com.registationSystem.regSys.repository.LessonsRepository;
import com.registationSystem.regSys.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {
    @Autowired
    private LessonsRepository lessonsRepository;

    public void create(LessonDAO lessonDAO) {
        lessonsRepository.save(lessonDAO);
    }

    public List<LessonDAO> readAll() {
        return lessonsRepository.findAll();
    }

    public LessonDAO read(int id) {
        return lessonsRepository.existsById(id)?
            lessonsRepository.findById(id).get():
            null;
    }

    public void update(LessonDAO lessonDAO, int id) {
        if (lessonsRepository.existsById(id)) {
            lessonDAO.setId(id);
            lessonsRepository.save(lessonDAO);
        }
    }

    public boolean delete(int id) {
        if (lessonsRepository.existsById(id)) {
            lessonsRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public List<LessonDAO> findByDate(Date date){
        return lessonsRepository.findByDate(date);
    }

}
