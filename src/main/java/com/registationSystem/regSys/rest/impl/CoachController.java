package com.registationSystem.regSys.rest.impl;

import com.registationSystem.regSys.dto.rq.RqCoachDTO;
import com.registationSystem.regSys.dto.rs.RsCoachDTO;
import com.registationSystem.regSys.dto.rs.RsLessonDTO;
import com.registationSystem.regSys.exception.FindException;
import com.registationSystem.regSys.exception.UpdateException;
import com.registationSystem.regSys.rest.CoachApi;
import com.registationSystem.regSys.service.CoachService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Getter
public class CoachController implements CoachApi {
    private final CoachService coachService;

    @Autowired
    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }

    @Override
    public ResponseEntity<?> create(@RequestBody RqCoachDTO rqCoachDTO) {
        coachService.create(rqCoachDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> update(RqCoachDTO rqCoachDTO, int coachId) throws UpdateException {
        try {
            coachService.update(rqCoachDTO, coachId);
        }
        catch (NoSuchElementException ex){
            throw new UpdateException("ID не существует");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<RsCoachDTO>> findAll() {
        return new ResponseEntity<>(coachService.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<RsLessonDTO>> getUnfinishedLessons(@RequestParam(name = "id") int coachId) throws FindException {
        try {
            return new ResponseEntity<>(coachService.getUnfinishedLessons(coachId), HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            throw new FindException("ID не существует");
        }
    }

    @Override
    public ResponseEntity<List<RsLessonDTO>> getFinishedLessons(@RequestParam(name = "id") int coachId) throws FindException {
        try {
            return new ResponseEntity<>(coachService.getFinishedLessons(coachId), HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            throw new FindException("ID не существует");
        }
    }
}
