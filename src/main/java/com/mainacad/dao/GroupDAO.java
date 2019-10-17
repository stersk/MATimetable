package com.mainacad.dao;

import com.mainacad.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupDAO extends JpaRepository<Group, Integer> {
}
