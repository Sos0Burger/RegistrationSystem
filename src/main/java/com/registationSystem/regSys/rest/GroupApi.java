package com.registationSystem.regSys.rest;

import com.registationSystem.regSys.dto.rq.RqGroupDTO;
import com.registationSystem.regSys.dto.rs.RsGroupDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/groups")
public interface GroupApi {
    @PostMapping()
   ResponseEntity<?> create(@RequestBody RqGroupDTO rqGroupDTO);
    @GetMapping()
    ResponseEntity<List<RsGroupDTO>> readAll();

    @PutMapping("/{id}")
    ResponseEntity<?> update(@RequestBody RqGroupDTO rqGroupDTO, @PathVariable(name = "id")int id);
    @GetMapping("/{id}")
    ResponseEntity<RsGroupDTO> findById(@PathVariable(name = "id")int id);
    @PostMapping("/{id}/{studentId}")
    ResponseEntity<?> register(@PathVariable(name = "id")int id, @PathVariable(name = "studentId")int studentId);
    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable(name = "id")int id);
}
