package com.registationSystem.regSys.Controllers;

import com.registationSystem.regSys.Models.Coach;
import com.registationSystem.regSys.Models.Group;
import com.registationSystem.regSys.Services.CoachService;
import com.registationSystem.regSys.Services.GroupService;
import com.registationSystem.regSys.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CoachesController {
    private final CoachService coachService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    public CoachesController(CoachService coachService) {
        this.coachService = coachService;
    }

    @PostMapping("/groups")
    public ResponseEntity<?> create(@RequestBody Coach coach) {
        coachService.create(coach);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/groups")
    public List<Coach> readAll(){
        return coachService.readAll();
    }

}
