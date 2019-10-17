package com.mainacad.dao;

import com.mainacad.entity.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorDAO extends JpaRepository<Administrator, Integer> {
}
