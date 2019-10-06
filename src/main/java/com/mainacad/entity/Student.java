package com.mainacad.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "students")
public class Student extends User {
  @Column(name = "user_role")
  private UserRole role = UserRole.STUDENT;

  @Column(name = "group")
  @ManyToOne(targetEntity = Group.class)
  private Group group;
}
