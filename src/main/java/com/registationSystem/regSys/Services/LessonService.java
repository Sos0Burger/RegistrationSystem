package com.registationSystem.regSys.Services;

import com.registationSystem.regSys.Models.Lesson;
import com.registationSystem.regSys.Repositories.LessonsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LessonService {
    @Autowired
    private LessonsRepository lessonsRepository;

    public void create(Lesson lesson) {
        lessonsRepository.save(lesson);
    }

    public List<Lesson> readAll() {
        return lessonsRepository.findAll();
    }

    public Lesson read(int id) {
        return lessonsRepository.findById(id).get();
    }

    public void update(Lesson lesson, int id) {
        if (lessonsRepository.existsById(id)) {
            lesson.setId(id);
            lessonsRepository.save(lesson);
        }
    }

    public boolean delete(int id) {
        if (lessonsRepository.existsById(id)) {
            lessonsRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public List<Lesson> findByCoachId(int id){
        return lessonsRepository.findByCoachId(id);
    }
    public List<Lesson> findByDate(Date date){
        return lessonsRepository.findByDate(date);
    }
}
