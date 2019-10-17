package com.mainacad.dao;

import com.mainacad.ApplicationRunner;
import com.mainacad.entity.Group;
import com.mainacad.entity.Specialization;
import com.mainacad.entity.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ApplicationRunner.class)
class StudentDAOTest {

  @Autowired
  StudentDAO studentDAO;

  @Autowired
  SpecializationDAO specializationDAO;

  @Autowired
  GroupDAO groupDAO;

  private List<Student> students;
  private List<Specialization> specializations;
  private List<Group> groups;

  @BeforeEach
  void setPreData(){
    Specialization specialization = new Specialization();
    specialization.setFaculty("it");

    Specialization savedSpecialization = specializationDAO.saveAndFlush(specialization);
    specializations = new ArrayList<>();
    specializations.add(savedSpecialization);

    Group group = new Group();
    group.setName("test_group");
    group.setSpecialization(savedSpecialization);

    Group savedGroup = groupDAO.saveAndFlush(group);
    groups = new ArrayList<>();
    groups.add(savedGroup);

    Group failedGroup = new Group();
    failedGroup.setName("failed_group");
    failedGroup.setSpecialization(savedSpecialization);

    failedGroup = groupDAO.saveAndFlush(failedGroup);
    groups.add(failedGroup);

    Student student = new Student();
    student.setEmail("test_email");
    student.setGroup(savedGroup);


    Student savedStudent = studentDAO.saveAndFlush(student);
    students = new ArrayList<>();
    students.add(savedStudent);

    Student failStudent = new Student();
    failStudent.setEmail("failed_email");
    failStudent.setGroup(failedGroup);

    students.add(studentDAO.saveAndFlush(failStudent));
  }

  @AfterEach
  void deleteData(){
    students.stream().forEach(student -> studentDAO.delete(student));
    groups.stream().forEach(group -> groupDAO.delete(group));
    specializations.stream().forEach(specialization -> specializationDAO.delete(specialization));
  }

  @Test
  void testFindAllByGroup(){
    List<Student> students = studentDAO.findByGroup(groups.get(0));

    assertNotNull(students);
    assertTrue(!students.isEmpty());
    assertEquals(1, students.size());
    assertEquals("test_email", students.get(0).getEmail());
  }
}