package com.registationSystem.regSys.Repositories;

import com.registationSystem.regSys.Models.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LessonsRepository extends JpaRepository<Lesson, Integer> {

    List<Lesson> findByDate(Date date);
}
