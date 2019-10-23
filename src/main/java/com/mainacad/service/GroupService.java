package com.mainacad.service;

import com.mainacad.entity.Group;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

public interface GroupService {
  Group findById(Integer id);
}
