package com.registationSystem.regSys.repository;

import com.registationSystem.regSys.dao.GroupDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface GroupsRepository extends JpaRepository<GroupDAO, Integer> {
}
