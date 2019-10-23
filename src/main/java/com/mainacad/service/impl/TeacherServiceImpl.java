package com.mainacad.service.impl;

import com.mainacad.dao.TeacherDAO;
import com.mainacad.entity.Teacher;
import com.mainacad.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {
  @Autowired
  TeacherDAO teacherDAO;

  @Override
  public Teacher getUserByEmail(String email) {
    return teacherDAO.findByEmailEquals(email);
  }

  @Override
  public Teacher save(Teacher teacher) {
    Teacher createdTeacher = null;

    Teacher checkedStudent = teacherDAO.findByEmailEquals(teacher.getEmail());
    if (checkedStudent == null) {
      createdTeacher = teacherDAO.save(teacher);
    }

    return createdTeacher;
  }
}
