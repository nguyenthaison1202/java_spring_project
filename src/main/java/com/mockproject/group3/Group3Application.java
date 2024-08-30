package com.mockproject.group3;

import com.mockproject.group3.enums.EnrollmentStatus;
import com.mockproject.group3.enums.Role;
import com.mockproject.group3.enums.Status;
import com.mockproject.group3.model.*;
import com.mockproject.group3.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class Group3Application {
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private InstructorRepository instructorRepository;
	@Autowired
	private EnrollmentRepository enrollmentRepository;
	@Autowired
	private LessonRepository lessonRepository;

	public static void main(String[] args) {
		SpringApplication.run(Group3Application.class, args);
	}
	@Bean
	public CommandLineRunner insertUsers() {
		return args -> {
			// Create and save Categories
			Category category1 = new Category();
			category1.setName("Technology");
			category1.setDescription("Courses on technology and programming");
			category1.setCreated_at(LocalDateTime.now());
			category1.setUpdated_at(LocalDateTime.now());
			categoryRepository.save(category1);

			Category category2 = new Category();
			category2.setName("Business");
			category2.setDescription("Courses on business and management");
			category2.setCreated_at(LocalDateTime.now());
			category2.setUpdated_at(LocalDateTime.now());
			categoryRepository.save(category2);

			// Create and save Instructors
			Users instructorUser1 = new Users();
			instructorUser1.setEmail("instructor1@example.com");
			instructorUser1.setPassword("password1");
			instructorUser1.setFull_name("Instructor One");
			instructorUser1.setAddress("123 Street, City");
			instructorUser1.setPhone("1234567890");
			instructorUser1.setCreate_at(LocalDateTime.now());
			instructorUser1.setUpdate_at(LocalDateTime.now());
			instructorUser1.setBlocked(false);
			instructorUser1.setRole(Role.INSTRUCTOR);
			usersRepository.save(instructorUser1);

			Instructor instructor1 = new Instructor();
			instructor1.setInstructor_code("INS001");
			instructor1.setFee(500.0);
			instructor1.setProfession_experience("5 years in software development");
			instructor1.setUser(instructorUser1);
			instructorRepository.save(instructor1);

			Users instructorUser2 = new Users();
			instructorUser2.setEmail("instructor2@example.com");
			instructorUser2.setPassword("password2");
			instructorUser2.setFull_name("Instructor Two");
			instructorUser2.setAddress("456 Avenue, City");
			instructorUser2.setPhone("0987654321");
			instructorUser2.setCreate_at(LocalDateTime.now());
			instructorUser2.setUpdate_at(LocalDateTime.now());
			instructorUser2.setBlocked(false);
			instructorUser2.setRole(Role.INSTRUCTOR);
			usersRepository.save(instructorUser2);

			Users studentuser1 = new Users();
			studentuser1.setEmail("student1@example.com");
			studentuser1.setPassword("password2");
			studentuser1.setFull_name("Student One");
			studentuser1.setAddress("456 Avenue, City");
			studentuser1.setPhone("0987654321");
			studentuser1.setCreate_at(LocalDateTime.now());
			studentuser1.setUpdate_at(LocalDateTime.now());
			studentuser1.setBlocked(false);
			studentuser1.setRole(Role.STUDENT);
			usersRepository.save(studentuser1);

			Student student1 = new Student();
			student1.setStudent_code("STU001");
			student1.setUser(studentuser1);
			studentRepository.save(student1);

			Instructor instructor2 = new Instructor();
			instructor2.setInstructor_code("INS002");
			instructor2.setFee(700.0);
			instructor2.setProfession_experience("10 years in business management");
			instructor2.setUser(instructorUser2);
			instructorRepository.save(instructor2);

			// Create and save Courses
			Course course1 = new Course();
			course1.setTitle("Java Programming");
			course1.setDescription("Learn Java from scratch.");
			course1.setPrice(99.99);
			course1.setStatus(Status.APPROVED);
			course1.setCreated_at(LocalDateTime.now());
			course1.setUpdated_at(LocalDateTime.now());
			course1.setCategory(category1);
			course1.setInstructor(instructor1);
			courseRepository.save(course1);

			Course course2 = new Course();
			course2.setTitle("Business Management");
			course2.setDescription("Learn how to manage a business.");
			course2.setPrice(149.99);
			course2.setStatus(Status.PENDING);
			course2.setCreated_at(LocalDateTime.now());
			course2.setUpdated_at(LocalDateTime.now());
			course2.setCategory(category2);
			course2.setInstructor(instructor2);
			courseRepository.save(course2);

			Course course3 = new Course();
			course3.setTitle("Advanced Python");
			course3.setDescription("Deep dive into Python programming.");
			course3.setPrice(199.99);
			course3.setStatus(Status.PENDING);
			course3.setCreated_at(LocalDateTime.now());
			course3.setUpdated_at(LocalDateTime.now());
			course3.setCategory(category1);
			course3.setInstructor(instructor1);
			courseRepository.save(course3);

			Enrollment enrollment1 = new Enrollment();
			enrollment1.setDescription("Enrolled in Java Programming course");
			enrollment1.setStatus(EnrollmentStatus.ENROLLED);
			enrollment1.setCourse(course1);
			enrollment1.setStudent(student1);
			enrollment1.setEnrollmentDate(LocalDateTime.now());
			enrollmentRepository.save(enrollment1);

			Lesson lesson1 = new Lesson();
			lesson1.setTitle("Introduction to Java");
			lesson1.setContent("This lesson covers the basics of Java programming.");
			lesson1.setCreated_at(LocalDateTime.now());
			lesson1.setUpdated_at(LocalDateTime.now());
			lesson1.setCourse(course1);
			lessonRepository.save(lesson1);

			Users adminUser1 = new Users();
			adminUser1.setEmail("admin@example.com");
			adminUser1.setPassword("password");
			adminUser1.setFull_name("Admin");
			adminUser1.setAddress("789 Boulevard, City");
			adminUser1.setPhone("1357924680");
			adminUser1.setCreate_at(LocalDateTime.now());
			adminUser1.setUpdate_at(LocalDateTime.now());
			adminUser1.setBlocked(false);
			adminUser1.setRole(Role.ADMIN);
			usersRepository.save(adminUser1);
		};
	}
}
