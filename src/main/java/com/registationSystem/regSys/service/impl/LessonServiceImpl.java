package com.registationSystem.regSys.service.impl;

import com.registationSystem.regSys.config.ApplicationSettings;
import com.registationSystem.regSys.dao.LessonDAO;
import com.registationSystem.regSys.dto.rq.RqLessonDTO;
import com.registationSystem.regSys.mapper.Mapper;
import com.registationSystem.regSys.repository.CoachesRepository;
import com.registationSystem.regSys.repository.GroupsRepository;
import com.registationSystem.regSys.repository.LessonsRepository;
import com.registationSystem.regSys.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {
    @Autowired
    private LessonsRepository lessonsRepository;
    @Autowired
    private GroupsRepository groupsRepository;
    @Autowired
    private CoachesRepository coachesRepository;

    public ResponseEntity<?> create(RqLessonDTO rqLessonDTO) {
        groupsRepository.findById(rqLessonDTO.getGroupId()).get();
        coachesRepository.findById(rqLessonDTO.getCoachId()).get();

        LessonDAO lessonDAO = Mapper.lessonDTOToLessonDAO(rqLessonDTO);
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
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public List<LessonDAO> findAll() {
        return lessonsRepository.findAll();
    }

    public LessonDAO find(int id) {
        return lessonsRepository.existsById(id) ?
                lessonsRepository.findById(id).get() :
                null;
    }

    public ResponseEntity<?> update(RqLessonDTO rqLessonDTO, int id) {
        groupsRepository.findById(rqLessonDTO.getGroupId()).get();
        coachesRepository.findById(rqLessonDTO.getCoachId()).get();

        LessonDAO lessonDAO = Mapper.lessonDTOToLessonDAO(rqLessonDTO);
        List<LessonDAO> dateLessonsList = lessonsRepository.findByDate(lessonDAO.getDate());


        ArrayList<Time> times = new ArrayList<>();
        times.add(ApplicationSettings.LESSON_START_TIME);
        times.add(ApplicationSettings.LESSON_END_TIME);

        //Проверка возможности записи на это время
        for (LessonDAO item : dateLessonsList
        ) {
            if (!(item.getTime().equals(lessonDAO.getTime()))) {
                times.add(item.getTime());
            }
        }
        Collections.sort(times);
        for (int i = 0; i < times.size() - 1; i++) {
            if (toMinutes(times.get(i)) + 60 <= toMinutes(lessonDAO.getTime()) &
                    toMinutes(times.get(i + 1)) - 60 >= toMinutes(lessonDAO.getTime())) {
                lessonDAO.setId(lessonDAO.getId());
                lessonDAO.setId(id);
                lessonsRepository.save(lessonDAO);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
