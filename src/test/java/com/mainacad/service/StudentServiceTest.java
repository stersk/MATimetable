package com.mainacad.service;

import com.mainacad.ApplicationRunner;
import com.mainacad.dao.StudentDAO;
import com.mainacad.entity.Group;
import com.mainacad.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ApplicationRunner.class)
class StudentServiceTest {
    @MockBean
    StudentDAO studentDAO;

    @Autowired
    StudentService studentService;

    @Test
    void testFindByGroup(){
        when(studentDAO.findByGroup(any(Group.class))).thenReturn(Arrays.asList(new Student()));
        List<Student> students = studentService.findByGroup(new Group());

        assertNotNull(students);
        assertTrue(!students.isEmpty());
    }
}