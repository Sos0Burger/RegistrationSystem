package com.registationSystem.regSys.Services;

import com.registationSystem.regSys.dao.CoachDAO;
import com.registationSystem.regSys.repository.CoachesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CoachService {
    @Autowired
    private CoachesRepository CoachesRepository;

    public void create(CoachDAO coachDAO) {
        CoachesRepository.save(coachDAO);
    }

    public List<CoachDAO> readAll() {
            return CoachesRepository.findAll();
        }
    public CoachDAO read(int id) {
        return CoachesRepository.existsById(id)?
                CoachesRepository.findById(id).get():
                null;
    }

    public void update(CoachDAO coachDAO, int id) {
        if (CoachesRepository.existsById(id)) {
            coachDAO.setId(id);
            CoachesRepository.save(coachDAO);
        }
    }

    public boolean delete(int id) {
        if (CoachesRepository.existsById(id)) {
            CoachesRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
