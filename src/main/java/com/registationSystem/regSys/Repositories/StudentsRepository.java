package com.registationSystem.regSys.Repositories;

import com.registationSystem.regSys.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentsRepository extends JpaRepository<Student, Integer> {
    List<Student> findByGroupId(int GroupId);
}