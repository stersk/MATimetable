package com.mainacad.dao;

import com.mainacad.entity.Group;
import com.mainacad.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentDAO extends JpaRepository<Student, Integer> {
  List<Student> findByGroup(Group group);
}
