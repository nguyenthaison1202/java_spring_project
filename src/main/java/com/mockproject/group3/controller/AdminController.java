package com.mockproject.group3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mockproject.group3.enums.Status;
import com.mockproject.group3.model.Instructor;
import com.mockproject.group3.model.Student;
import com.mockproject.group3.service.AdminService;
import com.mockproject.group3.service.InstructorService;
import com.mockproject.group3.service.StudentService;



@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private InstructorService instructorService;
    
    @GetMapping("/student")
    public ResponseEntity<List<Student>> getAllStudent() {
        List<Student> students = studentService.getAllStudent();
        return ResponseEntity.ok(students);
    }
    @GetMapping("/instructor")
    public ResponseEntity<List<Instructor>> getAllInstructor() {
        List<Instructor> students = instructorService.getAllInstructor();
        return ResponseEntity.ok(students);
    }
    @PutMapping("setBlock/{id}")
    public String BlockUsers(@PathVariable int id) {
        adminService.setBlockUsers(id);
        return "Change thành công";
        
    }
    @PutMapping("setStatusCourse/{id}")
    public String setStatusCourse(@PathVariable int id, @RequestBody Status status) {
        try {
            adminService.setStatusCourse(id, status);
            
            return "Set Status thành công";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }
}
