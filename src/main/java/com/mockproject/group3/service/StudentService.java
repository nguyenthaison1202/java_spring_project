package com.mockproject.group3.service;

import com.mockproject.group3.dto.ChangePasswordDTO;
import com.mockproject.group3.dto.StudentDTO;
import com.mockproject.group3.dto.UsersDTO;
import com.mockproject.group3.model.Student;
import com.mockproject.group3.model.Users;
import com.mockproject.group3.repository.StudentRepository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    //Create CRUD methods for Student
    public Student saveStudent(StudentDTO studentDto) {
        Student student = new Student();
        student.setStudent_code(studentDto.getStudent_code());
        student.setUser(studentDto.getUser());
        return studentRepository.save(student);
    }

    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }


    public void delete(int id) {

    }

    public boolean changePassword(int studentId, ChangePasswordDTO changePasswordDTO) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Không tìm thấy học sinh"));

        if (!changePasswordDTO.getCurrentPassword().equals(student.getUser().getPassword()) ) {
            throw new RuntimeException("Mật khẩu hiện tại không đúng");
        }

        if (changePasswordDTO.getNewPassword().equals(student.getUser().getPassword())) {
            throw new RuntimeException("Mật khẩu mới không được trùng với mật khẩu hiện tại.");
        }

        if (!changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmPassword())) {
            throw new RuntimeException("Xác nhận mật khẩu không khớp với mật khẩu mới");
        }

        student.getUser().setPassword(changePasswordDTO.getNewPassword());
        studentRepository.save(student);

        return true;
    }
    public Student updateProfile(int studentId,UsersDTO userDto){
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Không tìm thấy học sinh"));
        
        student.getUser().setFull_name(userDto.getFull_name());
        student.getUser().setAddress(userDto.getAddress());
        student.getUser().setPhone(userDto.getPhone());
        student.getUser().setUpdate_at(LocalDateTime.now());
        
        return studentRepository.save(student);
        
        }

}
