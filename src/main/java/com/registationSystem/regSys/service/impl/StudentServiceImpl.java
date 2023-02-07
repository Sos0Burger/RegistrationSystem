package com.registationSystem.regSys.service.impl;

import com.registationSystem.regSys.dao.GroupDAO;
import com.registationSystem.regSys.dao.LessonDAO;
import com.registationSystem.regSys.dao.StudentDAO;
import com.registationSystem.regSys.dto.rq.RqStudentDTO;
import com.registationSystem.regSys.dto.rs.RsLessonDTO;
import com.registationSystem.regSys.dto.rs.RsStudentDTO;
import com.registationSystem.regSys.exception.RegistrationException;
import com.registationSystem.regSys.mapper.Mapper;
import com.registationSystem.regSys.repository.GroupsRepository;
import com.registationSystem.regSys.repository.StudentsRepository;
import com.registationSystem.regSys.service.StudentService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentsRepository studentsRepository;
    @Autowired
    private GroupsRepository groupsRepository;

    public void create(RqStudentDTO rqStudentDTO) throws NoSuchElementException, RegistrationException {
        if (rqStudentDTO.getGroupId() != null) {
            GroupDAO groupDAO = groupsRepository.findById(rqStudentDTO.getGroupId()).get();
            if (groupDAO.getStudentsList().size() + 1 > groupDAO.getSize()) {
                if (rqStudentDTO.getAge() < groupDAO.getMinAge() || rqStudentDTO.getAge() > groupDAO.getMaxAge()) {
                    throw new RegistrationException("Возраст не подходит под требования группы");
                }
                throw new RegistrationException("Группа полная");
            }
        }
        studentsRepository.save(Mapper.studentDTOToStudentDAO(rqStudentDTO));
    }

    public List<RsStudentDTO> findAll() {
        List<RsStudentDTO> rsStudentDTOs = new ArrayList<>();
        for (StudentDAO studentDAO : studentsRepository.findAll()
        ) {
            rsStudentDTOs.add(Mapper.studentDAOToStudentDTO(studentDAO));
        }
        return rsStudentDTOs;
    }

    public StudentDAO find(int id) {
        return studentsRepository.findById(id).get();
    }

    public void update(RqStudentDTO rqStudentDTO, int id) {
        StudentDAO studentDAO = find(id);
        studentDAO.setGroupDAO(rqStudentDTO.getGroupId()==null?null:groupsRepository.findById(id).get());
        studentDAO.setAge(rqStudentDTO.getAge());
        studentDAO.setName(rqStudentDTO.getFirstName());
        studentDAO.setSurname(rqStudentDTO.getSurname());
        studentsRepository.save(studentDAO);
    }

    public void delete(int id) {
        StudentDAO studentDAO = studentsRepository.findById(id).get();
        studentDAO.setGroupDAO(null);
    }

    public List<RsLessonDTO> getScheduleById(int id) {
        if(studentsRepository.findById(id).get().getGroupDAO()==null) return null;
        List<RsLessonDTO> rsLessonDTOS = new ArrayList<>();
        Set<LessonDAO> lessonDAOList = groupsRepository.findById(studentsRepository.findById(id).get().getGroupDAO().getId()).get().getLessonsList();
        lessonDAOList.removeIf(LessonDAO::getIsDone);
        for (LessonDAO lessonDAO : lessonDAOList
        ) {
            rsLessonDTOS.add(Mapper.lessonDAOToLessonDTO(lessonDAO));
        }
        return rsLessonDTOS;
    }
}
