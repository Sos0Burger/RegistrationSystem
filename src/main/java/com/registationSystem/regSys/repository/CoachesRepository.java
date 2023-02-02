package com.registationSystem.regSys.repository;

import com.registationSystem.regSys.dao.CoachDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachesRepository extends JpaRepository<CoachDAO, Integer> {
}
