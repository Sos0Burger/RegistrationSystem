package com.registationSystem.regSys.Repositories;

import com.registationSystem.regSys.Models.StudentAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentAttendanceRepository extends JpaRepository<StudentAttendance, Integer> {
    List<StudentAttendance> findByStudent(int id);
}
