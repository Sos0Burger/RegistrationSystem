package com.registationSystem.regSys.ControllerInterInterfaces;

import com.registationSystem.regSys.Models.Group;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

public interface IGroupsController {
    @PostMapping("/groups")
   ResponseEntity<?> create(@RequestBody Group group);
    @GetMapping("/groups")
    List<Group> readAll();

    @PutMapping("/groups")
    ResponseEntity<?> update(@RequestBody Group group, int id);
    @PostMapping("/groups/{id}")
    ResponseEntity<Group> findById(@PathVariable(name = "id")int id);
    @PostMapping("/groups/{id}/{studentId}")
    ResponseEntity<?> register(@PathVariable(name = "id")int id, @PathVariable(name = "studentId")int studentId);
    @DeleteMapping("/groups/{id}")
    ResponseEntity<?> delete(@PathVariable(name = "id")int id);
}
