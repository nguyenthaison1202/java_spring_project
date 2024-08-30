package com.mockproject.group3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockproject.group3.enums.Status;
import com.mockproject.group3.model.Course;
import com.mockproject.group3.model.Instructor;
import com.mockproject.group3.model.Student;
import com.mockproject.group3.model.Users;
import com.mockproject.group3.repository.AdminRepository;
import com.mockproject.group3.repository.CourseRepository;
import com.mockproject.group3.repository.InstructorRepository;
import com.mockproject.group3.repository.StudentRepository;
import com.mockproject.group3.repository.UsersRepository;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UsersRepository usersRepository;

    public List<Users> getUsersList(){
        return usersRepository.findAll();
    }
    public List<Student> getStudentList(){
        return studentRepository.findAll();
    }
    public List<Instructor> getInstructorList(){
        return instructorRepository.findAll();
    }
    
    public Users setBlockUsers(int userId){
        Users user = usersRepository.findById(userId).orElseThrow(() -> new RuntimeException("Không tìm thấy User"));
        if (user.isBlocked()==true){
            user.setBlocked(false);
            
        }
        else{
            user.setBlocked(true);
        }
        return usersRepository.save(user);
    }
    
    public Course setStatusCourse(int courseId, Status status){
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Không tìm thấy khoá học"));
        course.setStatus(status);

        return courseRepository.save(course);
    }

}
