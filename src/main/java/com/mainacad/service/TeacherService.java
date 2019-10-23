package com.mainacad.service;

import com.mainacad.entity.Teacher;

public interface TeacherService {
  Teacher getUserByEmail(String email);
  Teacher save(Teacher student);
}
