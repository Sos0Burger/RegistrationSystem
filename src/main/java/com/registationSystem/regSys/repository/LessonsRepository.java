package com.registationSystem.regSys.repository;

import com.registationSystem.regSys.dao.LessonDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LessonsRepository extends JpaRepository<LessonDAO, Integer> {

    List<LessonDAO> findByDate(Date date);
}
