package com.registationSystem.regSys.Repositories;

import com.registationSystem.regSys.Entities.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachesRepository extends JpaRepository<Coach, Integer> {
}
