package com.registationSystem.regSys.Repositories;

import com.registationSystem.regSys.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentsRepository extends JpaRepository<Student, Integer> {
}
