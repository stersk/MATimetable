package com.mainacad.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "administrators")
public class Administrator extends User {
  @Column(name = "user_role")
  private final UserRole role = UserRole.ADMINISTRATOR;
}
