package com.mainacad.dao;

import com.mainacad.ApplicationRunner;
import com.mainacad.entity.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ApplicationRunner.class)
class LessonDAOTest {

    List<Specialization>    specializations;
    List<Group>             groups;
    List<Student>           students;
    List<Teacher>           teachers;
    List<Lesson>            lessons;

    @Autowired
    LessonDAO lessonDAO;

    @Autowired
    TeacherDAO teacherDAO;

    @Autowired
    StudentDAO studentDAO;

    @Autowired
    GroupDAO groupDAO;

    @Autowired
    SpecializationDAO specializationDAO;

    @BeforeEach
    void setPreData(){
        specializations   = new ArrayList<>();
        groups            = new ArrayList<>();
        students          = new ArrayList<>();
        teachers          = new ArrayList<>();
        lessons           = new ArrayList<>();

        Specialization specialization1 = specializationDAO.saveAndFlush(new Specialization(null, "IT Development"));
        Specialization specialization2 = specializationDAO.saveAndFlush(new Specialization(null, "IT Development"));
        Group group = groupDAO.saveAndFlush(new Group(null,"Kotlin", specialization1));
        Student student = new Student(UserRole.STUDENT, group);
        student.setEmail("test@email.com");
        student.setPassword("123456");
        Student storedStudent = studentDAO.saveAndFlush(student);

        Teacher teacher1 = new Teacher(UserRole.TEACHER, Subject.JAVA);
        teacher1.setEmail("teacher@email.com");
        teacher1.setPassword("654321");
        teacher1 = teacherDAO.saveAndFlush(teacher1);

        Teacher teacher2 = new Teacher(UserRole.TEACHER, Subject.C_PLUS_PLUS);
        teacher2.setEmail("teacher2@email.com");
        teacher2.setPassword("123456");
        teacher2 = teacherDAO.saveAndFlush(teacher2);

        //lecture
        Lesson lesson1 = lessonDAO.saveAndFlush(new Lesson(null, Subject.JAVA, true, null, specialization1, teacher1,
                LocalDateTime.of(2019, 10, 1, 19, 30, 0), "1"));
        //lecture
        Lesson lesson2 = lessonDAO.saveAndFlush(new Lesson(null, Subject.JAVA, true, null, specialization1, teacher1,
                LocalDateTime.of(2019, 10, 3, 19, 30, 0), "4"));
        //lecture
        Lesson lesson3 = lessonDAO.saveAndFlush(new Lesson(null, Subject.C_PLUS_PLUS, true, null, specialization2, teacher2,
                LocalDateTime.of(2019, 10, 3, 19, 30, 0), "3"));
        //notLecture
        Lesson lesson4 = lessonDAO.saveAndFlush(new Lesson(null, Subject.JAVA, false, group, null, teacher1,
                LocalDateTime.of(2019, 10, 24, 19, 30, 0), "24"));

        specializations.add(specialization1);
        specializations.add(specialization2);
        groups.add(group);
        students.add(storedStudent);
        teachers.add(teacher1);
        teachers.add(teacher2);
        lessons.add(lesson1);
        lessons.add(lesson2);
        lessons.add(lesson3);
        lessons.add(lesson4);
    }

    @AfterEach
    void deletePreData(){
        lessons.stream().forEach(it -> lessonDAO.delete(it));
        teachers.stream().forEach(it -> teacherDAO.delete(it));
        students.stream().forEach(it -> studentDAO.delete(it));
        groups.stream().forEach(it -> groupDAO.delete(it));
        specializations.stream().forEach(it -> specializationDAO.delete(it));
    }

    @Test
    void testFindLessonsByStudent(){
        LocalDateTime from = LocalDateTime.of(2019, 10, 24, 0, 0, 0);
        LocalDateTime to = LocalDateTime.of(2019, 10, 24, 23, 59, 59);

        List<Lesson> testObject = lessonDAO.findByStudentAndStartBeforeAndStartTimeAfter(students.get(0).getEmail(), from, to);

        assertNotNull(testObject);
        assertEquals(1, testObject.size());
        assertEquals(lessons.get(3), testObject.get(0));
        assertEquals("24", testObject.get(0).getCabinet());

        from = LocalDateTime.of(2019, 10, 3, 0, 0, 0);
        to = LocalDateTime.of(2019, 10, 3, 23, 59, 59);

        testObject = lessonDAO.findByStudentAndStartBeforeAndStartTimeAfter(students.get(0).getEmail(), from, to);

        assertNotNull(testObject);
        assertEquals(1, testObject.size());
        assertTrue(testObject.get(0).getIsLecture());
        assertEquals(specializations.get(0), testObject.get(0).getSpecialization());
        assertEquals("4", testObject.get(0).getCabinet());

        from = LocalDateTime.of(2019, 10, 1, 0, 0, 0);

        testObject = lessonDAO.findByStudentAndStartBeforeAndStartTimeAfter(students.get(0).getEmail(), from, to);

        assertNotNull(testObject);
        assertEquals(2, testObject.size());
        assertTrue(testObject.get(0).getIsLecture());
        assertTrue(testObject.get(1).getIsLecture());
        assertEquals(specializations.get(0), testObject.get(0).getSpecialization());
        assertEquals(specializations.get(0), testObject.get(1).getSpecialization());
    }

    @Test
    void testFindLessonsByGroup(){
        LocalDateTime from = LocalDateTime.of(2019, 10, 24, 0, 0, 0);
        LocalDateTime to = LocalDateTime.of(2019, 10, 24, 23, 59, 59);
        List<Lesson> testObject = lessonDAO.findByGroupAndStartTimeAfterAndStartTimeBefore(groups.get(0), from, to);

        assertNotNull(testObject);
        assertEquals(1, testObject.size());
        assertEquals("24", testObject.get(0).getCabinet());
    }

    @Test
    void testFindLessonsBySpecialisationAndPeriod(){
        LocalDateTime from = LocalDateTime.of(2019, 10, 3, 0, 0, 0);
        LocalDateTime to = LocalDateTime.of(2019, 10, 3, 23, 59, 59);

        List<Lesson> testObject = lessonDAO.findBySpecializationAndAndStartTimeAfterAndStartTimeBefore(specializations.get(0), from, to);

        assertNotNull(testObject);
        assertEquals(2, testObject.size());
        assertEquals("4", testObject.get(0).getCabinet());

        from = LocalDateTime.of(2019, 10, 1, 0, 0, 0);
        to = LocalDateTime.of(2019, 10, 1, 23, 59, 59);

        testObject = lessonDAO.findBySpecializationAndAndStartTimeAfterAndStartTimeBefore(specializations.get(0), from, to);

        assertNotNull(testObject);
        assertEquals(1, testObject.size());
        assertEquals("1", testObject.get(0).getCabinet());
    }

    @Test
    void testFindLessonsByTeacher(){
        LocalDateTime from = LocalDateTime.of(2019, 10, 3, 0, 0, 0);
        LocalDateTime to = LocalDateTime.of(2019, 10, 3, 23, 59, 59);

        List<Lesson> testObject = lessonDAO.findByTeacherAndAndStartTimeAfterAndStartTimeBefore(teachers.get(0), from, to);

        assertNotNull(testObject);
        assertEquals(1, testObject.size());
        assertEquals("3", testObject.get(0).getCabinet());
    }

    @Test
    void testFindLessonsByPeriod(){
        LocalDateTime from = LocalDateTime.of(2019, 10, 1, 0, 0, 0);
        LocalDateTime to = LocalDateTime.of(2019, 10, 1, 23, 59, 59);

        List<Lesson> testObject = lessonDAO.findByAndStartTimeAfterAndStartTimeBefore(from, to);
        assertNotNull(testObject);
        assertEquals(1, testObject.size());

        to = LocalDateTime.of(2019, 10, 4, 23, 59, 59);

        testObject = lessonDAO.findByAndStartTimeAfterAndStartTimeBefore(from, to);
        assertNotNull(testObject);
        assertEquals(3, testObject.size());

        to = LocalDateTime.of(2019, 10, 31, 23, 59, 59);

        testObject = lessonDAO.findByAndStartTimeAfterAndStartTimeBefore(from, to);
        assertNotNull(testObject);
        assertEquals(4, testObject.size());
    }
}