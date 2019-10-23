package com.mainacad.service;

import com.mainacad.entity.Administrator;

public interface AdministratorService {
  Administrator getUserByEmail(String email);
  Administrator save(Administrator administrator);
}
