package com.mainacad.service.impl;

import com.mainacad.dao.StudentDAO;
import com.mainacad.entity.Group;
import com.mainacad.entity.Student;
import com.mainacad.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
  @Autowired
  StudentDAO studentDAO;

  @Override
  public List<Student> findByGroup(Group group) {
    return studentDAO.findByGroup(group);
  }
}
