package com.mainacad.service.impl;

import com.mainacad.dao.AdministratorDAO;
import com.mainacad.entity.Administrator;
import com.mainacad.entity.Teacher;
import com.mainacad.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministratorServiceImpl implements AdministratorService {
  @Autowired
  AdministratorDAO administratorDAO;

  @Override
  public Administrator getUserByEmail(String email) {
    return administratorDAO.findByEmailEquals(email);
  }

  @Override
  public Administrator save(Administrator administrator) {
    Administrator createdAdministrator = null;

    Administrator checkedAdministrator = administratorDAO.findByEmailEquals(administrator.getEmail());
    if (checkedAdministrator == null) {
      createdAdministrator = administratorDAO.save(administrator);
    }

    return createdAdministrator;
  }
}
