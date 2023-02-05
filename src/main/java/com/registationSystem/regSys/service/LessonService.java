package com.registationSystem.regSys.service;

import com.registationSystem.regSys.dao.LessonDAO;
import com.registationSystem.regSys.dto.rq.RqLessonDTO;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

public interface LessonService {
    ResponseEntity<?> create(RqLessonDTO rqLessonDTO);


    List<LessonDAO> readAll();

    LessonDAO read(int id);

    void update(LessonDAO lessonDAO, int id);

    boolean delete(int id);

    List<LessonDAO> findByDate(Date date);
}
