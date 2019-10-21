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
          "SELECT l1.* FROM lessons l1 " +
                  "JOIN students s1 ON l1.group_id=s1.group_id " +
                  "WHERE l1.start_time >= :time1 " +
                  "AND l1.start_time <= :time2 " +
                  "AND s1.email = :email " +
          "UNION " +
                  "SELECT l2.* FROM lessons l2 " +
                  "JOIN groups g2 ON l2.specialization_id=g2.specialization_id " +
                  "JOIN students s2 ON g2.id=s2.group_id " +
                  "WHERE l2.start_time >= :time1 " +
                  "AND l2.start_time <= :time2 " +
                  "AND s2.email = :email"
  )
  List<Lesson> findByStudentAndStartBeforeAndStartTimeAfter(@Param("email") String email, @Param("time1")  LocalDateTime time1, @Param("time2")  LocalDateTime time2);
}
