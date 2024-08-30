package com.mockproject.group3.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mockproject.group3.dto.request.course.CourseParamReq;
import com.mockproject.group3.dto.request.course.CreateCourseReq;
import com.mockproject.group3.dto.request.course.InstructorCourseParamReq;
import com.mockproject.group3.dto.request.course.SubmitCourseReq;
import com.mockproject.group3.dto.request.course.UpdateCourseReq;
import com.mockproject.group3.dto.response.course.CourseWithRatingRes;
import com.mockproject.group3.enums.Status;
import com.mockproject.group3.exception.AppException;
import com.mockproject.group3.exception.ErrorCode;
import com.mockproject.group3.model.Category;
import com.mockproject.group3.model.Course;
import com.mockproject.group3.model.Instructor;
import com.mockproject.group3.repository.CategoryRepository;
import com.mockproject.group3.repository.CourseRepository;
import com.mockproject.group3.repository.InstructorRepository;
import com.mockproject.group3.utils.GetAuthUserInfo;

@Service
public class CourseService {
    private CourseRepository courseRepository;
    private CategoryRepository categoryRepository;
    private InstructorRepository instructorRepository;
    private GetAuthUserInfo getAuthUserInfo;

    public CourseService(CourseRepository courseRepository, CategoryRepository categoryRepository,
            InstructorRepository instructorRepository, GetAuthUserInfo getAuthUserInfo) {
        this.courseRepository = courseRepository;
        this.categoryRepository = categoryRepository;
        this.instructorRepository = instructorRepository;
        this.getAuthUserInfo = getAuthUserInfo;
    }

    // @PreAuthorize("hasRole('STUDENT')")
    public Page<CourseWithRatingRes> getAllForStudent(CourseParamReq req) {
        PageRequest pageRequest = PageRequest.of(req.getPage() - 1 >= 0 ? req.getPage() - 1 : 0, req.getPageSize());
        Sort sort = Sort.by("title").descending();

        if (req.getSortBy().length > 0) {
            sort = combineIntoSort(req.getSortBy(), req.getSortDirection());
        }

        if (req.getCategoryId() == 0) {
            return courseRepository.findByKeywordOnlyActivate(req.getKeyword(),
                    pageRequest.withSort(sort));
        }

        return courseRepository.findByKeywordWithCategoryOnlyActivate(req.getKeyword(),
                req.getCategoryId(),
                pageRequest.withSort(sort));
    }

    // @PreAuthorize("hasRole('INSTRUCTOR')")
    public Page<Course> getAllForInstructor(InstructorCourseParamReq req) {
        int userId = getAuthUserInfo.getAuthUserId();
        PageRequest pageRequest = PageRequest.of(req.getPage() - 1 >= 0 ? req.getPage() - 1 : 0, req.getPageSize());
        Sort sort = Sort.by("title").descending().and(Sort.by("updated_at").descending());

        instructorRepository.findById(1)
                .orElseThrow(() -> new AppException(ErrorCode.INSTRUCTOR_NOTFOUND));

        return courseRepository.findCourseByInstructor(req.getKeyword(), 1, pageRequest.withSort(sort));
    }

    private Sort combineIntoSort(String[] sortBy, String[] sortDirection) {
        List<Sort.Order> orders = new ArrayList<>();
        Sort.Direction direction = Sort.Direction.DESC;

        for (int i = 0; i < sortBy.length; i++) {
            direction = Sort.Direction.DESC;
            if (sortDirection.length > i) {
                direction = sortDirection[i].equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
            }
            orders.add(new Sort.Order(
                    direction,
                    sortBy[i]));
        }
        return Sort.by(orders);
    }

    // @PreAuthorize("hasRole('INSTRUCTOR')")
    public Course create(CreateCourseReq req) {
        // get user ID from authen
        int userId = getAuthUserInfo.getAuthUserId();
        Instructor instructor = instructorRepository.findById(1)
                .orElseThrow(() -> new AppException(ErrorCode.INSTRUCTOR_NOTFOUND));
        Category category = categoryRepository.findById(req.getCategoryId())
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOTFOUND));
        Course newCourse = new Course();
        newCourse.setTitle(req.getTitle());
        newCourse.setDescription(req.getDescription());
        newCourse.setPrice(req.getPrice());
        newCourse.setCategory(category);
        newCourse.setStatus(Status.CREATED);
        newCourse.setCreated_at(LocalDateTime.now());
        newCourse.setUpdated_at(LocalDateTime.now());
        newCourse.setInstructor(instructor);

        return courseRepository.save(newCourse);
    }

    // @PreAuthorize("hasRole('INSTRUCTOR')")
    public Course update(int courseId, UpdateCourseReq req) {
        int userId = getAuthUserInfo.getAuthUserId();
        Instructor instructor = instructorRepository.findById(1)
                .orElseThrow(() -> new AppException(ErrorCode.INSTRUCTOR_NOTFOUND));
        Category category = categoryRepository.findById(req.getCategoryId())
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOTFOUND));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new AppException(ErrorCode.COURSE_NOTFOUND));

        // if (course.getInstructor().getId() != userId)
        // throw new AppException(ErrorCode.ACTION_NOT_ALLOW);

        course.setTitle(req.getTitle());
        course.setDescription(req.getDescription());
        course.setPrice(req.getPrice());
        course.setCategory(category);
        course.setUpdated_at(LocalDateTime.now());

        return courseRepository.save(course);
    }

    // @PreAuthorize("hasRole('INSTRUCTOR')")
    public void delete(int courseId) {
        courseRepository.deleteById(courseId);
    }

    // @PreAuthorize("hasRole('INSTRUCTOR')")
    public void submitCourse(SubmitCourseReq req) {
        Course course = courseRepository.findById(req.getId())
                .orElseThrow(() -> new AppException(ErrorCode.COURSE_NOTFOUND));

        if (course.getStatus() != Status.APPROVED) {
            course.setStatus(Status.PENDING);
            course.setUpdated_at(LocalDateTime.now());
            courseRepository.save(course);
        } else
            throw new AppException(ErrorCode.SUBMIT_COURSE_FAIL);
    }
}
