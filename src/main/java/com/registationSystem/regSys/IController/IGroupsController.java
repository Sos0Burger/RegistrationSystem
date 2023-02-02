package com.registationSystem.regSys.IController;

import com.registationSystem.regSys.Entities.Group;
import com.registationSystem.regSys.Models.GroupModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

public interface IGroupsController {
    @PostMapping()
   ResponseEntity<?> create(@RequestBody GroupModel groupModel);
    @GetMapping()
    ResponseEntity<List<GroupModel>> readAll();

    @PutMapping()
    ResponseEntity<?> update(@RequestBody GroupModel groupModel);
    @GetMapping("/{id}")
    ResponseEntity<GroupModel> findById(@PathVariable(name = "id")int id);
    @PostMapping("/{id}/{studentId}")
    ResponseEntity<?> register(@PathVariable(name = "id")int id, @PathVariable(name = "studentId")int studentId);
    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable(name = "id")int id);
}
