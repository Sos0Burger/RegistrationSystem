package com.registationSystem.regSys.Repositories;

import com.registationSystem.regSys.Models.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupsRepository extends JpaRepository<Group, Integer> {
}
