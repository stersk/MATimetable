package com.mainacad.controller;

import com.mainacad.entity.*;
import com.mainacad.service.AdministratorService;
import com.mainacad.service.GroupService;
import com.mainacad.service.StudentService;
import com.mainacad.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Target;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private GroupService groupService;

    @GetMapping(path="/get/{email}")
    public ResponseEntity<User> getUser(@PathVariable String email) {
        User user = studentService.getUserByEmail(email);

        if (user == null) {
            user = teacherService.getUserByEmail(email);
        }

        if (user == null) {
            user = teacherService.getUserByEmail(email);
        }

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }

    }

    @PostMapping(path="/student")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        if (student.getGroup() != null) {
            Group checkedGroup = groupService.findById(student.getGroup().getId());
            if (checkedGroup == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

        Student savedStudent = studentService.save(student);
        if (savedStudent != null) {
            return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path="/teacher")
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
        Teacher savedTeacher = teacherService.save(teacher);
        if (savedTeacher != null) {
            return new ResponseEntity<>(savedTeacher, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path="/administrator")
    public ResponseEntity<Administrator> createAdministrator(@RequestBody Administrator administrator) {
        Administrator savedAdministrator = administratorService.save(administrator);
        if (savedAdministrator != null) {
            return new ResponseEntity<>(savedAdministrator, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /*@PutMapping()
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User updatedUser = userService.update(user);
        if (updatedUser != null) {
            return new ResponseEntity(updatedUser, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path="/get-by-id/{id}")
    public ResponseEntity<User> getOne(@PathVariable Integer id) {
        User userFromDB = userService.findById(id);
        if (userFromDB != null){
            return new ResponseEntity(userFromDB, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path="/get-all")
    public ResponseEntity<List> getAll() {
        List<User> users = userService.findAll();
        return new ResponseEntity(users, HttpStatus.OK);
    }*/

    @DeleteMapping(path="/{role}/{id}")
    public ResponseEntity deleteUser(@PathVariable UserRole role, @PathVariable Integer id) {
    //        userService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
