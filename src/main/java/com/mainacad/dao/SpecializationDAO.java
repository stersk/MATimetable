package com.mainacad.dao;

import com.mainacad.entity.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecializationDAO extends JpaRepository<Specialization, Integer> {
}
