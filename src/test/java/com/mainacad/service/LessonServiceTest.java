package com.mainacad.service;

import com.mainacad.ApplicationRunner;
import com.mainacad.dao.LessonDAO;
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
class LessonServiceTest {

    @MockBean
    LessonDAO lessonDAO;

//    @Autowired
//    LessonService lessonService;

    @Test
    void testFindLessonsByStudent(){

    }

    @Test
    void testFindLessonsByGroup(){

    }

    @Test
    void testFindLessonsBySpecialisation(){

    }

    @Test
    void testFindLessonsByTeacher(){

    }

    @Test
    void testFindLessonsByPeriod(){

    }
}