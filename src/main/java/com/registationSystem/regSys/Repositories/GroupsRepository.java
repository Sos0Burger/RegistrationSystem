package com.registationSystem.regSys.Repositories;

import com.registationSystem.regSys.Models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface GroupsRepository extends JpaRepository<Group, Integer> {
}
