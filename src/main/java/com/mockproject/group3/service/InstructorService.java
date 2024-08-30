package com.mockproject.group3.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockproject.group3.dto.ChangePasswordDTO;
import com.mockproject.group3.dto.InstructorDTO;
import com.mockproject.group3.dto.UsersDTO;
import com.mockproject.group3.model.Instructor;
import com.mockproject.group3.repository.InstructorRepository;

@Service
public class InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    public List<Instructor> getAllInstructor() {
        return instructorRepository.findAll();
    }

    public Instructor saveInstructor(InstructorDTO instructorDto) {
        Instructor instructor = new Instructor();
        instructor.setInstructor_code(instructorDto.getInstructor_code());
        instructor.setProfession_experience(instructorDto.getProfession_experience());
        instructor.setFee(instructorDto.getFee());
        instructor.setPayouts(instructorDto.getPayout());
        instructor.setSubscriptions(instructorDto.getSubscription());
        instructor.setUser(instructorDto.getUser());

        return instructorRepository.save(instructor);
    }

    public boolean changePassword(int instructorId, ChangePasswordDTO changePasswordDTO) {
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy học sinh"));

        if (!changePasswordDTO.getCurrentPassword().equals(instructor.getUser().getPassword())) {
            throw new RuntimeException("Mật khẩu hiện tại không đúng");
        }

        if (changePasswordDTO.getNewPassword().equals(instructor.getUser().getPassword())) {
            throw new RuntimeException("Mật khẩu mới không được trùng với mật khẩu hiện tại.");
        }

        if (!changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmPassword())) {
            throw new RuntimeException("Xác nhận mật khẩu không khớp với mật khẩu mới");
        }

        instructor.getUser().setPassword(changePasswordDTO.getNewPassword());
        instructorRepository.save(instructor);

        return true;
    }

    public Instructor updateProfile(int instructorId, UsersDTO userDto) {
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy học sinh"));

        instructor.getUser().setFull_name(userDto.getFull_name());
        instructor.getUser().setAddress(userDto.getAddress());
        instructor.getUser().setPhone(userDto.getPhone());
        instructor.getUser().setUpdate_at(LocalDateTime.now());

        return instructorRepository.save(instructor);
    }
}
