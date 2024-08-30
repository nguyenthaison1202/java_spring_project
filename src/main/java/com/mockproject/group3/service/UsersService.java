package com.mockproject.group3.service;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockproject.group3.dto.ChangePasswordDTO;
import com.mockproject.group3.dto.UsersDTO;
import com.mockproject.group3.model.Users;
import com.mockproject.group3.repository.UsersRepository;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    //Create CRUD methods for Users
    public Users saveUser(UsersDTO usersDto) {
        Users users = new Users();
        users.setEmail(usersDto.getEmail());
        users.setPassword(usersDto.getPassword());
        users.setFull_name(usersDto.getFull_name());
        users.setAddress(usersDto.getAddress());
        users.setPhone(usersDto.getPhone());
        users.setRole(usersDto.getRole());
        users.setCreate_at(LocalDateTime.now());
        users.setUpdate_at(LocalDateTime.now());
        users.setBlocked(false);
        return usersRepository.save(users);
    }

    public Users update(Users users) {
        return null;
    }


    public Users findByEmail(String email) {
        return null;
    }

    public void delete(int id) {

    }


    // public boolean changePassword(int userId, ChangePasswordDTO changePasswordDTO) {
    //     Users user = usersRepository.findById(userId).orElseThrow(() -> new RuntimeException("Không tìm thấy User"));

    //     if (!changePasswordDTO.getCurrentPassword().equals(user.getPassword()) ) {
    //         throw new RuntimeException("Mật khẩu hiện tại không đúng");
    //     }

    //     if (changePasswordDTO.getNewPassword().equals(user.getPassword())) {
    //         throw new RuntimeException("Mật khẩu mới không được trùng với mật khẩu hiện tại.");
    //     }

    //     if (!changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmPassword())) {
    //         throw new RuntimeException("Xác nhận mật khẩu không khớp với mật khẩu mới");
    //     }

    //     user.setPassword(changePasswordDTO.getNewPassword());
    //     usersRepository.save(user);

    //     return true;
    // }


    // public Users updateProfile(int userId,UsersDTO userDto){
    //     Users user = usersRepository.findById(userId).orElseThrow(() -> new RuntimeException("Không tìm thấy User"));

    //     user.setFull_name(userDto.getFull_name());
    //     user.setEmail(userDto.getEmail());
    //     user.setAddress(userDto.getAddress());
    //     user.setPhone(userDto.getPhone());
    //     user.setUpdate_at(LocalDateTime.now());
    //     return usersRepository.save(user);
        
    // }

}
