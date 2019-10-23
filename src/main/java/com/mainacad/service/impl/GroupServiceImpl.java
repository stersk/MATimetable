package com.mainacad.service.impl;

import com.mainacad.dao.GroupDAO;
import com.mainacad.entity.Group;
import com.mainacad.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {
  @Autowired
  GroupDAO groupDAO;

  @Override
  public Group findById(Integer id) {
    return groupDAO.findById(id).orElse(null);
  }
}
