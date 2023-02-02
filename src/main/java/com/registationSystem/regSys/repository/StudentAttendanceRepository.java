package com.registationSystem.regSys.repository;

import com.registationSystem.regSys.dao.StudentAttendanceDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentAttendanceRepository extends JpaRepository<StudentAttendanceDAO, Integer> {
}
