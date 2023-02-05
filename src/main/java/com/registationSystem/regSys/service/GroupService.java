package com.registationSystem.regSys.service;

import com.registationSystem.regSys.dao.GroupDAO;
import com.registationSystem.regSys.dto.rq.RqGroupDTO;
import com.registationSystem.regSys.dto.rs.RsGroupDTO;
import com.registationSystem.regSys.dto.rs.RsStudentDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GroupService {
    void create(RqGroupDTO rqGroupDTO);
    List<RsGroupDTO> readAll();

    GroupDAO read(int id);

    ResponseEntity<?> update(RqGroupDTO rqGroupDTO, int id);

   void delete(int id);
   ResponseEntity<?> registration(int groupId, int studentId);

   List<RsStudentDTO> getStudents(int groupId);
}
