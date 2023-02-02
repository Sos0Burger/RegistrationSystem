package com.registationSystem.regSys.service;

import com.registationSystem.regSys.dao.LessonDAO;

import java.util.Date;
import java.util.List;

public interface LessonService {
    void create(LessonDAO lessonDAO);

    List<LessonDAO> readAll();

    LessonDAO read(int id);

    void update(LessonDAO lessonDAO, int id);

    boolean delete(int id);

    List<LessonDAO> findByDate(Date date);
}
