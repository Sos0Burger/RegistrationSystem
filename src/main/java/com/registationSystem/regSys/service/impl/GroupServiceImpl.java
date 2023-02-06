package com.registationSystem.regSys.service.impl;

import com.registationSystem.regSys.dao.GroupDAO;
import com.registationSystem.regSys.dao.StudentDAO;
import com.registationSystem.regSys.dto.rq.RqGroupDTO;
import com.registationSystem.regSys.dto.rq.RqStudentDTO;
import com.registationSystem.regSys.dto.rs.RsGroupDTO;
import com.registationSystem.regSys.dto.rs.RsStudentDTO;
import com.registationSystem.regSys.mapper.Mapper;
import com.registationSystem.regSys.repository.GroupsRepository;
import com.registationSystem.regSys.repository.StudentsRepository;
import com.registationSystem.regSys.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupsRepository groupsRepository;
    @Autowired
    StudentsRepository studentsRepository;

    public void create(RqGroupDTO rqGroupDTO) {
        groupsRepository.save(Mapper.groupDTOToGroupDAO(rqGroupDTO));
    }

    public List<RsGroupDTO> readAll() {
        List<RsGroupDTO> rsGroupDTOS = new ArrayList<>();
        for (GroupDAO groupDAO : groupsRepository.findAll()
        ) {
            rsGroupDTOS.add(Mapper.groupDAOToGroupDTO(groupDAO));
        }
        return rsGroupDTOS;
    }

    public GroupDAO read(int id) {
        return groupsRepository.findById(id).get();
    }

    public ResponseEntity<?> update(RqGroupDTO rqGroupDTO, int id) {
        GroupDAO groupDAO = groupsRepository.findById(id).get();

        if (groupDAO.getStudentsList().size() < rqGroupDTO.getSize()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            groupDAO.setSize(rqGroupDTO.getSize());
        }

        //Поиск минимального и максимального возраста студента если в группе есть студенты
        if (groupDAO.getStudentsList().size() != 0) {
            Integer minAge = null;
            Integer maxAge = null;
            for (StudentDAO studentDAO : groupDAO.getStudentsList()
            ) {
                if (minAge == null || minAge > studentDAO.getAge()) {
                    minAge = studentDAO.getAge();
                }
                if (maxAge == null || maxAge < studentDAO.getAge()) {
                    maxAge = studentDAO.getAge();
                }
            }
            if (!(minAge >= rqGroupDTO.getMinAge() && maxAge <= rqGroupDTO.getMaxAge()))
                return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        groupDAO.setMinAge(rqGroupDTO.getMinAge());
        groupDAO.setMaxAge(rqGroupDTO.getMaxAge());
        groupsRepository.save(groupDAO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> registration(int groupId, int studentId){

        GroupDAO groupDAO = groupsRepository.findById(groupId).get();
        StudentDAO studentDAO = studentsRepository.findById(studentId).get();

        if(!(groupDAO.getStudentsList().size()+1<= groupDAO.getSize())){
            if(!(studentDAO.getAge()>= groupDAO.getMinAge() && studentDAO.getAge()<= groupDAO.getMaxAge())){
                return new ResponseEntity<>("Age is not suitable",HttpStatus.NOT_ACCEPTABLE);
            }
            return new ResponseEntity<>("Group is full", HttpStatus.NOT_ACCEPTABLE);
        }
        studentDAO.setGroupDAO(groupDAO);
        studentsRepository.save(studentDAO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    public void delete(int id) {
        GroupDAO groupDAO = groupsRepository.findById(id).get();
        for (StudentDAO studentDAO : groupDAO.getStudentsList()) {
            studentDAO.setGroupDAO(null);
            studentsRepository.save(studentDAO);
        }
    }

    @Override
    public List<RsStudentDTO> getStudents(int groupId) {
        List<RsStudentDTO> rsGroupDTOs = new ArrayList<>();
        for (StudentDAO student:groupsRepository.findById(groupId).get().getStudentsList()
             ) {
            rsGroupDTOs.add(Mapper.studentDAOToStudentDTO(student));
        }
        return rsGroupDTOs;
    }
}
