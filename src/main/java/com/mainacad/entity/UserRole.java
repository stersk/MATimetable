package com.mainacad.entity;

import lombok.Getter;

@Getter
public enum UserRole {
  STUDENT("student"),
  TEACHER("teacher"),
  ADMINISTRATOR("administrator");

  private final String name;

  UserRole(String name) {
    this.name = name;
  }
}
