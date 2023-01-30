package com.registationSystem.regSys.Repositories;

import com.registationSystem.regSys.Models.StudentAttendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentAttendanceRepository extends JpaRepository<StudentAttendance, Integer> {
    List<StudentAttendance> findByStudentId(int id);
}
