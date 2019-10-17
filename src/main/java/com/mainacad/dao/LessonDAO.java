package com.mainacad.dao;

import com.mainacad.entity.Group;
import com.mainacad.entity.Lesson;
import com.mainacad.entity.Specialization;
import com.mainacad.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface LessonDAO extends JpaRepository<Lesson, Integer> {
  List<Lesson> findByGroupAndStartTimeAfterAndStartTimeBefore(Group group, LocalDateTime from, LocalDateTime to);

  List<Lesson> findBySpecializationAndAndStartTimeAfterAndStartTimeBefore(Specialization specialization, LocalDateTime from, LocalDateTime to);

  List<Lesson> findByTeacherAndAndStartTimeAfterAndStartTimeBefore(Teacher teacher, LocalDateTime from, LocalDateTime to);

  List<Lesson> findByAndStartTimeAfterAndStartTimeBefore(LocalDateTime from, LocalDateTime to);

  @Query(nativeQuery = true, value =
          "SELECT l.* FROM lessons l " +
                  "JOIN students s ON l.group_id=s.group_id " +
                  "WHERE l.start_time >= :time1 " +
                  "AND l.start_time <= :time2 " +
                  "AND s.email = :email")
  List<Lesson> findNotLectureByStudentAndStartTimeAfterAndStartTimeBefore(@Param("email") String email, @Param("time1")  LocalDateTime time1, @Param("time2")  LocalDateTime time2);
}
