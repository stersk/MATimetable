package com.mainacad.service;

import com.mainacad.entity.Group;
import com.mainacad.entity.Student;

import java.util.List;

public interface StudentService {
    Student getUserByEmail(String email);
    List<Student> findByGroup(Group group);
    Student save(Student student);
}
