package com.mainacad.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lessons")
public class Lesson {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne(targetEntity = Subject.class)
  private Subject subject;

  @Column(name = "is_lecture")
  private Boolean isLecture;

  @ManyToOne(targetEntity = Group.class)
  private Group group;

  @ManyToOne(targetEntity = Specialization.class)
  private Specialization specialization;

  @ManyToOne(targetEntity = Teacher.class)
  private Teacher teacher;

  @Column(name = "start_time")
  private LocalDateTime time;
}
