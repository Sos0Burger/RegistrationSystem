package com.registationSystem.regSys.service.impl;

import com.registationSystem.regSys.dao.CoachDAO;
import com.registationSystem.regSys.dao.LessonDAO;
import com.registationSystem.regSys.dto.rq.RqCoachDTO;
import com.registationSystem.regSys.dto.rs.RsCoachDTO;
import com.registationSystem.regSys.dto.rs.RsLessonDTO;
import com.registationSystem.regSys.mapper.Mapper;
import com.registationSystem.regSys.repository.CoachesRepository;
import com.registationSystem.regSys.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CoachServiceImpl implements CoachService {
    @Autowired
    private CoachesRepository coachesRepository;

    public void create(RqCoachDTO rqCoachDTO) {
        coachesRepository.save(Mapper.coachDTOToCoachDAO(rqCoachDTO));
    }

    public List<RsCoachDTO> findAll() {
        List<RsCoachDTO> rsCoachDTOS = new ArrayList<>();
        for (CoachDAO coachDAO : coachesRepository.findAll()
        ) {
            rsCoachDTOS.add(Mapper.coachDAOToCoachDTO(coachDAO));
        }
        return rsCoachDTOS;
    }

    public RsCoachDTO find(int id) {
        return Mapper.coachDAOToCoachDTO(coachesRepository.findById(id).get());
    }

    public void update(RqCoachDTO rqCoachDTO, int id) {
        CoachDAO coachDAO = coachesRepository.findById(id).get();
        coachDAO.setName(rqCoachDTO.getName());
        coachDAO.setSurname(rqCoachDTO.getSurname());
        coachDAO.setPhoneNumber(rqCoachDTO.getPhoneNumber());
        coachDAO.setEmail(rqCoachDTO.getEmail());
        coachDAO.setId(id);
        coachesRepository.save(coachDAO);
    }

    public void delete(int id) {
        coachesRepository.deleteById(id);
    }

    @Override
    public List<RsLessonDTO> getUnfinishedLessons(int coachId) {
        List<RsLessonDTO> rsLessonDTOS = new ArrayList<>();
        Set<LessonDAO> lessonDAOSet = coachesRepository.findById(coachId).get().getLessonDAOList();
        lessonDAOSet.removeIf(LessonDAO::getIsDone);
        for (LessonDAO lessonDAO : lessonDAOSet
        ) {
            rsLessonDTOS.add(Mapper.lessonDAOToLessonDTO(lessonDAO));
        }
        return rsLessonDTOS;
    }

    @Override
    public List<RsLessonDTO> getFinishedLessons(int coachId) {
        List<RsLessonDTO> rsLessonDTOs = new ArrayList<>();
        Set<LessonDAO> lessonDAOSet = coachesRepository.findById(coachId).get().getLessonDAOList();
        lessonDAOSet.removeIf(lesson -> !lesson.getIsDone());
        for (LessonDAO lessonDAO : lessonDAOSet
        ) {
            rsLessonDTOs.add(Mapper.lessonDAOToLessonDTO(lessonDAO));
        }
        return rsLessonDTOs;
    }
}
