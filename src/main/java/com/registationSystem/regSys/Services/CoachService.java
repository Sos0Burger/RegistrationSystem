package com.registationSystem.regSys.Services;

import com.registationSystem.regSys.Entities.Coach;
import com.registationSystem.regSys.Repositories.CoachesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CoachService {
    @Autowired
    private CoachesRepository CoachesRepository;

    public void create(Coach coach) {
        CoachesRepository.save(coach);
    }

    public List<Coach> readAll() {
            return CoachesRepository.findAll();
        }
    public Coach read(int id) {
        return CoachesRepository.existsById(id)?
                CoachesRepository.findById(id).get():
                null;
    }

    public void update(Coach coach, int id) {
        if (CoachesRepository.existsById(id)) {
            coach.setId(id);
            CoachesRepository.save(coach);
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
