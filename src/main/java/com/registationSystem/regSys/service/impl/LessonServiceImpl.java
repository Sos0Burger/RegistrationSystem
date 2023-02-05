package com.registationSystem.regSys.service.impl;

import com.registationSystem.regSys.config.ApplicationSettings;
import com.registationSystem.regSys.dao.LessonDAO;
import com.registationSystem.regSys.dto.rq.RqLessonDTO;
import com.registationSystem.regSys.mapper.Mapper;
import com.registationSystem.regSys.repository.GroupsRepository;
import com.registationSystem.regSys.repository.LessonsRepository;
import com.registationSystem.regSys.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.*;

@Service
public class LessonServiceImpl implements LessonService {
    @Autowired
    private LessonsRepository lessonsRepository;
    @Autowired
    private GroupsRepository groupsRepository;

    public ResponseEntity<?> create(RqLessonDTO rqLessonDTO) {
        LessonDAO lessonDAO = Mapper.lessonDTOToLessonDAO(rqLessonDTO);
        //Поиск группы по ID
        if (lessonDAO.getGroupDAO() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        //Поиск тренера по ID
        if (lessonDAO.getCoachDAO() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<LessonDAO> dateLessonsList = lessonsRepository.findByDate(lessonDAO.getDate());
        ArrayList<Time> times = new ArrayList<>();
        times.add(ApplicationSettings.LESSON_START_TIME);
        times.add(ApplicationSettings.LESSON_END_TIME);

        //Проверка возможности записи на это время
        for (LessonDAO item : dateLessonsList
        ) {
            times.add(item.getTime());
        }
        Collections.sort(times);
        for (int i = 0; i < times.size() - 1; i++) {
            if (toMinutes(times.get(i)) + 60 <= toMinutes(lessonDAO.getTime()) &
                    toMinutes(times.get(i + 1)) - 60 >= toMinutes(lessonDAO.getTime())) {
                lessonDAO.setId(lessonDAO.getId());
                lessonsRepository.save(lessonDAO);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
        }
        //Это надо поменять, наверное
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    public List<LessonDAO> readAll() {
        return lessonsRepository.findAll();
    }

    public LessonDAO read(int id) {
        return lessonsRepository.existsById(id) ?
                lessonsRepository.findById(id).get() :
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

    public List<LessonDAO> findByDate(Date date) {
        return lessonsRepository.findByDate(date);
    }

    public int toMinutes(Time time) {
        return (time.getHours() * 60 + time.getMinutes());
    }

}
