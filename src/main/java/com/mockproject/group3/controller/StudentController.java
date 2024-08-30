package com.mockproject.group3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mockproject.group3.dto.ChangePasswordDTO;
import com.mockproject.group3.dto.StudentDTO;
import com.mockproject.group3.dto.UsersDTO;
import static com.mockproject.group3.enums.Role.STUDENT;
import com.mockproject.group3.model.Student;
import com.mockproject.group3.model.Users;
import com.mockproject.group3.service.StudentService;
import com.mockproject.group3.service.UsersService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private UsersService usersService;

    
    
    @PostMapping("/register")
    public Student createStudent(@Valid @RequestBody UsersDTO userDto) {
        userDto.setRole(STUDENT);
        Users user = usersService.saveUser(userDto);
        StudentDTO studentDto = new StudentDTO();
        studentDto.setStudent_code("JAVA-FPT0" + user.getId());
        studentDto.setUser(user);
        return studentService.saveStudent(studentDto);
    }

    @PostMapping("/change-password/{userId}")
    public String changePassword(@PathVariable int userId, @Valid @RequestBody ChangePasswordDTO changePasswordDTO) {
        try {
            studentService.changePassword(userId, changePasswordDTO);
            return "Thay đổi password thành công";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }
    @PutMapping("/update-profile/{userId}")
    public String updateProfile(@PathVariable int userId, @Valid @RequestBody UsersDTO userDto){
        try {
            studentService.updateProfile(userId, userDto);
            return "Thay đổi thông tin thành công";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }
    
    
}
