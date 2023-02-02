package com.registationSystem.regSys.controller;

import com.registationSystem.regSys.dto.rs.RsGroupDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface GroupsController {
    @PostMapping()
   ResponseEntity<?> create(@RequestBody RsGroupDTO rsGroupDTO);
    @GetMapping()
    ResponseEntity<List<RsGroupDTO>> readAll();

    @PutMapping()
    ResponseEntity<?> update(@RequestBody RsGroupDTO rsGroupDTO);
    @GetMapping("/{id}")
    ResponseEntity<RsGroupDTO> findById(@PathVariable(name = "id")int id);
    @PostMapping("/{id}/{studentId}")
    ResponseEntity<?> register(@PathVariable(name = "id")int id, @PathVariable(name = "studentId")int studentId);
    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable(name = "id")int id);
}
