package com.registationSystem.regSys.repository;

import com.registationSystem.regSys.dao.StudentDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentsRepository extends JpaRepository<StudentDAO, Integer> {
}
