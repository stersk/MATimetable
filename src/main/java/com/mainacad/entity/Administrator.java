package com.mainacad.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Data
@NoArgsConstructor
@Table(name = "administrators")
public class Administrator extends User {
  @JsonInclude()
  @Transient
  private final UserRole role = UserRole.ADMINISTRATOR;
}
