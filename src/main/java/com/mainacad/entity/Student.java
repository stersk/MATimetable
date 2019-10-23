package com.mainacad.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "students")
public class Student extends User {
  @JsonInclude()
  @Transient
  private UserRole role = UserRole.STUDENT;

  @ManyToOne(targetEntity = Group.class)
  private Group group;
}
