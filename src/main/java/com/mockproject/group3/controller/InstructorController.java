package com.mockproject.group3.controller;

import static com.mockproject.group3.enums.Role.INSTRUCTOR;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mockproject.group3.dto.ChangePasswordDTO;
import com.mockproject.group3.dto.InstructorDTO;
import com.mockproject.group3.dto.UsersDTO;
import com.mockproject.group3.model.Instructor;
import com.mockproject.group3.model.Users;
import com.mockproject.group3.service.InstructorService;
import com.mockproject.group3.service.UsersService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/instructor")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private UsersService usersService;

    @PostMapping("/register")
    public Instructor createInstructor(@Valid @RequestBody UsersDTO userDto) {
        userDto.setRole(INSTRUCTOR);
        Users user = usersService.saveUser(userDto);
        InstructorDTO instructorDto = new InstructorDTO();
        instructorDto.setInstructor_code("JAVA-FPT1" + user.getId());
        instructorDto.setProfession_experience("Amateur");
        instructorDto.setFee(0);
        instructorDto.setPayout(null);
        instructorDto.setSubscription(null);
        instructorDto.setUser(user);
        return instructorService.saveInstructor(instructorDto);
    }

    @PostMapping("/change-password/{userId}")
    public String changePassword(@PathVariable int userId, @Valid @RequestBody ChangePasswordDTO changePasswordDTO) {
        try {
            instructorService.changePassword(userId, changePasswordDTO);
            return "Thay đổi password thành công";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    @PutMapping("/update-profile/{userId}")
    public String updateProfile(@PathVariable int userId, @Valid @RequestBody UsersDTO userDto) {
        try {
            instructorService.updateProfile(userId, userDto);
            return "Thay đổi thông tin thành công";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }
}
