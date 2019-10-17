package com.mainacad.dao;

import com.mainacad.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherDAO extends JpaRepository<Teacher, Integer> {
}
